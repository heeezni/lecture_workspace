package com.sinse.dbproject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/*JTable은 껍데기에 불과하므로, 연동할 테이블이 수 백개라 할 지라도
 * TableModel은 1개면 충분하다. 바뀌는 건 쿼리문만*/


/*WindonwListener를 포함하여 이벤트 리스너 중 재정의할 메서드의 수가 너무 많은 경우
 * 사용하지도 않는 부모의 메서드를 클래스 코드 안에 남겨놔야 하는 상황 발생? 
 * */

public class EmpLoad extends JFrame{
	JPanel p_north;
	JButton bt_emp;
	JButton bt_dept;
	JButton bt_excel;
	
	DataModel model; //JTable이 참조하는 Model 객체
	JTable table;
	JScrollPane scroll;	
	
	//윈도우 프레임이 뜰 때 한 번 접속하고, 윈도우 닫을 때 데이터 베이스 닫자
	String url="jdbc:mysql://localhost:3306/dev";
	String user="java";
	String pwd="1234";
	Connection con; //윈도우 창 닫으면 접속을 끊어야 하므로, 멤버변수로 빼놓기
						  //Connection은 접속 정보를 가진 객체이므로, 접속을 끊을 수도 있다.
	
	JFileChooser chooser=new JFileChooser(); //파일탐색기
	
	public EmpLoad() {
		p_north = new JPanel();
		bt_emp = new JButton("사원테이블 로드");
		bt_dept = new JButton("부서테이블 로드");
		bt_excel = new JButton("엑셀에서 로드");
		
		table = new JTable(); //테이블과 모델 연결은 반드시 생성자에서만 할 수 있는 건 아니다.
		scroll = new JScrollPane(table);
		
		//style
		p_north.setPreferredSize(new Dimension(800, 30));
		
		//assemble (조립)
		p_north.add(bt_emp);
		p_north.add(bt_dept);
		p_north.add(bt_excel);
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		
		/*이벤트 구현 시 정의되는 클래스는 재사용성이 없으므로, 굳이 .java까지 정의해가면서 개발할 필요 X
		 * like JS의 익명함수 
		 * 내부 클래스 중 이름 없는 클래스를 가리켜 '익명 내부 클래스'라고 한다
		 * 주로 일회성 객체 사용 시 (이벤트)
		 * 익명 내부 클래스는, 자신을 감싸고 있는 바깥쪽 외부 클래스의 멤버들을 같이 사용할 수 있다.
		 * 즉 접근할 수 있다는 점이 장점
		 * */
		bt_emp.addActionListener(new ActionListener(){ //익명내부클래스 (얘가 귀)
			@Override
			public void actionPerformed(ActionEvent e) {
				loadTable("select * from emp");
			}
		});
		bt_dept.addActionListener(new ActionListener(){ //익명내부클래스 (얘가 귀)
			@Override
			public void actionPerformed(ActionEvent e) {
				loadTable("select * from dept");
			}
		});
		bt_excel.addActionListener(new ActionListener(){ //익명내부클래스 (얘가 귀)
			@Override
			public void actionPerformed(ActionEvent e) {
				int result=chooser.showOpenDialog(EmpLoad.this);//this 안됨. 익명 클래스 지칭함
				/*외부클래스.this ➡ 외부클래스의 인스턴스 접근
				내부 클래스는 외부클래스의 인스턴스 접근시, 클래스명.this*/
				if(result==JFileChooser.APPROVE_OPTION) { //파일열기를 수락했다면
					loadExcel();
				}
			}
		});
		
		//addWindowListener(new MyWindowAdapter());
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) { //익명내부클래스로 구현
				//System.out.println("창 닫았어?");
				if(con!=null) {
					try {
						con.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				System.exit(0); //process kill
			}
		});
		connect(); //db접속
		
		setSize(800,630);
		setVisible(true);
	}
	
	//데이터베이스 접속
	public void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url, user, pwd);
			
			if(con!=null) {
				this.setTitle("접속 성공");
			}else {
				this.setTitle("접속 실패");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//사원 테이블 가져오기
	public void loadTable(String query) {
		String sql=query;
		ResultSet rs=null; //결과 표를 표현한 객체
		//현재의 rs자체는 JTable이 이해할 수 없는 상태이므로, TableModel에 rs를 가공하여 넣어주면 됨
		
		//쿼리 수행 객체 생성
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); //객체 생성
			rs=pstmt.executeQuery(); //표반환 (커서의 초기 위치는 첫번째 레코드보다 더 위쪽)
			
			//현재 select문의 대상이 되는 테이블의 컬럼 정보를 프로그래밍에서 얻어오려면
			//ResultMetaData라는 객체를 이용하면 됨
			ResultSetMetaData metaData=pstmt.getMetaData();
			int colCount=metaData.getColumnCount();
			
			//rs는 몇 층까지 일까?
			rs.last(); //가장 마지막 row로 보내버림
			int total=rs.getRow(); //총 레코드 수
			
			/*레코드 수와 컬럼 수를 알아냈으니, 모델 객체가 보유한 (현재 null)
			*이차원 배열을 메모리에 올리자*/
			model = new DataModel();
			model.data=new String[total][colCount]; //모델이 보유한 data (이차원배열)
			model.title=new String[colCount]; //모델이 보유한 title (일차원배열)
			
			for(int i=0; i<colCount; i++ ) { //컬럼 수 만큼 반복문 수행
				model.title[i]=metaData.getColumnName(i+1); //1번째부터 가져옴
			}
			
			//rs의 데이터를 이차원배열로 옮겨 
			rs.beforeFirst(); //rs 커서 원 위치 
			
			int index=0; //층수를 접근하기 위한 index
			while(rs.next()) {
				
				for(int i=0; i<colCount; i++) { //어떤 테이블인지는 모르나, 그 테이블의 컬럼수만큼 반복
					model.data[index][i]=rs.getString(i+1);
				}
				index++;
			}
			//✅모든데이터가 완성되었으므로, JTable의 모델을 동적으로 적용
			table.setModel(model);
			table.updateUI();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(pstmt!=null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
	}
	
	/*java 자체적인 api에는 엑셀을 연동하는 기능 X
	 * Apache에서 배포하는 POI라는 패키지를 연동해보자*/ 
	public void loadExcel() {
		
	}
	
	public static void main(String[] args) {
		new EmpLoad();
	}
}