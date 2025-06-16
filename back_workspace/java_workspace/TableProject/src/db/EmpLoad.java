package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/*Emp 테이블에 있는 레코드를 모두 가져와서 JTable에 출력*/
public class EmpLoad extends JFrame{
	
	JTable table;
	JScrollPane scroll;
	
	String[][] data=null; //몇 건인지 알 수 없으므로 배열을 아직 생성 못함(null)
	
	String[] columns= {
		"empno","ename","job","mgr","hiredate","sal","comm","deptno"	
	};

	public void loadData() {
		String url="jdbc:mysql://localhost:3306/dev";
		String user="java";
		String pwd="1234";
		
		Connection con = null; //시도가 아니라, 접속 시도 후 그 정보를 가진 객체
		//이 객체를 이용하여 접속을 종류할 수 있음
		PreparedStatement pstmt = null; //쿼리문을 수행하는 객체
		ResultSet rs = null; //표 데이터를 표현한 객체
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Load Success!!");
			
			con = DriverManager.getConnection(url, user, pwd);
			if(con!=null) {
				System.out.println("접속 성공!");
				
				String sql = "select * from emp order by empno asc";
				pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				//TYPE_SCROLL_INSENSITIVE : 커서가 전방향, 후방향 및 건너뛰기 가능하게, 커서의 위치 자유자재 조절 가능
				//CONCUR_READ_ONLY : ResultSet에 담은 레코드를 읽기 전용으로만 쓰겠다 
				
				rs = pstmt.executeQuery(); //select문은 ResultSet으로 결과를 받음
				
				rs.last(); //레코드 내에서 마지막 행으로 강제 이동
				System.out.println("제가 지금 있는 곳의 위치는"+rs.getRow());
				int total=rs.getRow();
				
				//rs가 데이터 베이스 연동 결과 이므로, 바로 이 시점부터 배열의 크기를 결정지을 수 있음
				
				//자바 뿐 아니라 대부분의 언어에서 배열은 선언 시 반드시 그 크기를 결정해야함
				data=new String[total][8];
				
				//아래의 코드를 반복문으로 처리하면서 층수 변경 + rs.next() 커서도 한 칸씩 이동
				//주의! 현재 총 레코드 수를 얻어오는 바람에 커서는 제일 아래에 위치함 (반복문 돌리기 전에 다시 수거하기)
				rs.beforeFirst();
				//first()는 레코드 맨 위로, beforeFirst();는 완전 초기화
				int i=0;				
				while(rs.next()) {
				//한 사원에 대한 정보 처리
				data[i][0]=rs.getString("empno"); 
				data[i][1]=rs.getString("ename"); 
				data[i][2]=rs.getString("job"); 
				data[i][3]=rs.getString("mgr"); 
				data[i][4]=rs.getString("hiredate");
				data[i][5]=rs.getString("sal"); 
				data[i][6]=rs.getString("comm"); 
				data[i][7]=rs.getString("deptno");
					i++;
				}
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public EmpLoad() {
		//mysql에서 이미 사원정보를 가져왔어야함
		loadData();
			
		table=new JTable(data, columns);
		scroll=new JScrollPane(table);
		
		add(scroll);
		
		setSize(800,600);
		setVisible(true);		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new EmpLoad();
	}
}
