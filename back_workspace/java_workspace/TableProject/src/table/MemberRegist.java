package table;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

public class MemberRegist extends JFrame implements ActionListener, WindowListener, MouseListener{
	//서쪽 영역
	JPanel p_west;
	JTextField t_id;
	JTextField t_name;
	JTextField t_tel;
	JButton bt;
	
	//센터영역
	JPanel p_center;
	JTable table;
	JScrollPane scroll;
	
	//남쪽에 정보출력
	JPanel p_south;
	JLabel la_id;
	JLabel la_value;
	JLabel la_name;
	JLabel la_tel;
	JTextField txt_name;
	JTextField txt_tel;
	
	JButton bt_edit;
	JButton bt_del;
	
	TableModel model;
	int index=0; //몇 번째 층에 회원을 넣을 지 결정짓는 인덱스
	
	Connection con=null; //접속은 윈도우창 생성 시 한 번 시도되며, 창 닫을 때 접속해제
	int member4_id; // 현재 사용자가 보고 있는 회원의 pk값
	String[] member; //현재 유저가 선택한 한 회원의 모든 정보
	public MemberRegist() {
		//생성
		p_west=new JPanel();
		t_id=new JTextField();
		t_name=new JTextField();
		t_tel=new JTextField();
		
		//애플리케이션의 퀄리티를 높이기 위해 이미지 적용해보기
		//단, 이미지 경로는 플랫폼에 의존적인 경로말고, 중립적인 **클래스패스** 기준으로 가져오자
		Class myClass=this.getClass(); //Class는 패키지 접근 가능
		URL url=myClass.getClassLoader().getSystemResource("create.png"); //url반환	
		
		bt= new JButton("가입");
		
		p_center=new JPanel();
		table=new JTable(model=new MyModel(this)); //JTable은 껍데기에 지나지 않기 때문에, 
																			// **실제 보여질 데이터는 모델이 결정한다!**
		/*table=new JTable(이 테이블에 줘야할 데이터/데이터 처리 객체 : TableModel자료형); 
		JTable은 MVC패턴을 어느 정도 반영한 컴포넌트이다 (완벽하진 않음  모델+컴포넌트)
		생성자에 이차원 배열을 넣는 방식은 불편
		생성하는 시점에 언제나 데이터가 있어야한다는 전제조건 필요해서
		*/
		scroll=new JScrollPane(table);
		//새로 추가된 센터영역 컴포넌트
		p_south=new JPanel();
		la_id=new JLabel("ID");
		la_value=new JLabel("");
		la_name=new JLabel("Name");
		la_tel=new JLabel("Tel");
		txt_name=new JTextField();
		txt_tel=new JTextField();
		bt_edit=new JButton("수정");
		bt_del=new JButton("삭제");
		
		//style 적용
		p_west.setBackground(Color.ORANGE);
		p_west.setPreferredSize(new Dimension(150,500));
		
		Dimension d=new Dimension(146,35);
		t_id.setPreferredSize(d);
		t_name.setPreferredSize(d);
		t_tel.setPreferredSize(d);
		
		scroll.setPreferredSize(new Dimension(420,350));
		
		//새로운 컴포넌트에 대한 스타일
		p_south.setPreferredSize(new Dimension(420,145));
		p_south.setBackground(Color.YELLOW);
		
		Dimension d2=new Dimension(200,30);
		la_id.setPreferredSize(d2);
		la_name.setPreferredSize(d2);
		la_tel.setPreferredSize(d2);
		Dimension d3=new Dimension(360, 30);
		la_value.setPreferredSize(d3);
		txt_name.setPreferredSize(d3);
		txt_tel.setPreferredSize(d3);
		
		//조립
		p_west.add(t_id);
		p_west.add(t_name);
		p_west.add(t_tel);
		p_west.add(bt);
		add(p_west, BorderLayout.WEST);
		
		p_center.add(scroll);
		add(p_center);
		add(p_south, BorderLayout.SOUTH);
		
		//남쪽 패널에 부착
		p_south.add(la_id);
		p_south.add(la_value);
		p_south.add(la_name);
		p_south.add(txt_name);
		p_south.add(la_tel);
		p_south.add(txt_tel);
		p_south.add(bt_edit);
		p_south.add(bt_del);
		
		bt.addActionListener(this);
		//윈도우과 리스너와의 연결
		this.addWindowListener(this);
		table.addMouseListener(this); //마우스리스너와 테이블과의 연결
		bt_edit.addActionListener(this);
		bt_del.addActionListener(this);
		
		setBounds(100,300,600,500);
		setVisible(true);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		connect(); 	//윈도우 띄우자마자 데이터 접속
		selectAll(); //윈도우 띄우자마자 테이블 보여주기
	}
	
	public void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/dev";
			String id="java";
			String pwd="1234";
			
			con=DriverManager.getConnection(url, id, pwd);	
			if(con!=null) {
				this.setTitle("접속성공");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//회원 한 명 등록(model이 보유한 이차원배열의 한 공간을 들어가게 할 예정 , 즉 원하는 층에 들어갈 예정)
	public void regist() {
		//회원 1명을 일차원배열에 담아서, 
		//그 일차원 배열을 모델이 보유한 2차원 배열의 가장 최근위치에 밀어넣기!
		/*String[] member={t_id.getText(),t_name.getText(), t_tel.getText()};
		((MyModel)model).rows[index++]=member;
		//테이블 갱신
		table.updateUI();*/
		
		String sql="insert into member4(id, name, tel) ";
		sql+="values('"+t_id.getText()+"','"+t_name.getText()+"','"+t_tel.getText()+"')";	
		System.out.println(sql); //콘솔에 출력된 SQL문을 실제 DB에서 실행했을 때 성공되어야 함
		
		PreparedStatement pstmt=null;
		
		try {
			pstmt=con.prepareStatement(sql);
			int result=pstmt.executeUpdate(); //DML (insert, update, delete)
			
			if(result>0) {
				JOptionPane.showMessageDialog(this, "등록 성공");
				selectAll();
			}else {
				JOptionPane.showMessageDialog(this,"등록 실패");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt!=null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	public void selectAll() {
		String sql="select * from member4";
		PreparedStatement pstmt=null; //finally에서 닫으려고 한줄로 처리 안 함.
		ResultSet rs=null;
		
		try {
			pstmt=con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			//select 문이니까 executeQuery사용
			rs=pstmt.executeQuery(); //select 문일 경우, 쿼리 수행 시 표가 반환되므로 해당표럴 제어할 ResultSet객체가 반환됨
			
			rs.last(); //레코드의 마지막으로 포인터를 보낸다.
			int total=rs.getRow(); //현재 위치 어디지 묻는다
			System.out.println("현재까지 가입한 총 수는 "+total);
			
			/*rs 자체는 MyModel이 보유하고 있는 2차원 배열 자체가 아니므로, 
			 * rs의 데이터를 2차원 배열로 변환하여
			 * MyModel이 보유한 배열대신 사용해야함 (대체)*/
			((MyModel)model).rows=new String[total][4]; //pk값 표현을 위해
			
			//마지막 위치로 보냈던 rs의 커서를 다시 처음으로 복귀시킨다.
			//레코드를 처음부터 차례대로 접근하기 위함
			rs.beforeFirst();
			
			//한 건의 레코드 출력
			int index=0;
			while(rs.next()) {
			
				String[] record= {
						rs.getString("id"), rs.getString("name"), rs.getString("tel"), rs.getString("member4_id")
				};
				((MyModel)model).rows[index++]=record;
			}
			table.updateUI(); //테이블 갱신 updateUI: 시스템이 그려놓은 그림을 다시그린다
			
		} catch (SQLException e) {
			e.printStackTrace();
			}finally {
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
		}
	}
	
	//선택된 회원만 가져오기
	public void select(int member4_id) {
		//System.out.println("사원 선택했어?");

		String sql="select * from member4 where member4_id="+member4_id; //클릭하는 사람이 pk 결정 → 매개변수
		System.out.println(sql);
		
		//쿼리문이 검증되었으므로 JDBC통해 네트워크로 전송하기
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		
		try {
			pstmt=con.prepareStatement(sql);//쿼리문 객체 생성
			rs=pstmt.executeQuery(); //레코드 결과 반환 (1건)
			
			//화면에 출력
			if(rs.next()) { //실행부가 꼭대기에 가있으니까 일단 한 칸 전진 + 레코드가 있다면(회원이 있을때만) 아래의 코드 수행
				la_value.setText(rs.getString("id"));
				txt_name.setText(rs.getString("name"));
				txt_tel.setText(rs.getString("tel"));
			}
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
		}
	}
	
	//선택된 회원 1명 삭제
	public void delete(int member4_id) {
		
		String sql="delete from member4 where member4_id="+member4_id;
		//System.out.println(sql);
		
		PreparedStatement pstmt=null;
		
		try {
			pstmt=con.prepareStatement(sql);
			
			//DML수행시, 이 쿼리 수행에 의해 영향을 받은 레코드 수가 반환
			//개발자는 이 반환값으로 실행 성공여부를 판단 / 0인 경우는 실패 (에러는 아님)
			int result=pstmt.executeUpdate();
			
			if(result>0) {
					JOptionPane.showMessageDialog(this, "삭제 성공");
					//MyModel이 보유한 예전 이차원 배열을 업데이트 하도록 처리
					selectAll();
					//table.updateUI(); //컴포넌트 새로고침 (selectAll)에 들어있음
				}else {
					JOptionPane.showMessageDialog(this, "삭제되지 않았습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//현재 회원의 정보 수정
	public void edit(String[] member) { //한 사람에 대한 정보를 담는 배열을 매개변수로 선언
		//이 메서드를 호출하는 자는 한 사람에 대한 정보를 '배열'로 담아서 전달
		String sql="update member4 set id='"+member[0]+"', name='"+member[1]+"', tel='"+member[2]+"'";
		sql+=" where member4_id="+member[3];
		//System.out.println(sql);
		
		PreparedStatement pstmt=null;
		try {
			pstmt=con.prepareStatement(sql);
			int result = pstmt.executeUpdate(); //update DML 수행
			if(result>0) {
				JOptionPane.showMessageDialog(this, "수정성공");
				selectAll(); //MyModel의 이차원배열 갱신 및 테이블 updateUI()포함
			} else {
				JOptionPane.showMessageDialog(this, "변경사항이 반영되지 않았습니다.");				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	
	public static void main(String[] args) {
		new MemberRegist();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();
		
		if(obj==bt) { //등록이라면
			regist();
		}else if(obj==bt_edit) { //수정이라면
			int result=JOptionPane.showConfirmDialog(this, "수정하시겠어요?"); //alert창의 부모 컴포넌트는 this윈도우창
			if(result==JOptionPane.OK_OPTION) {
				//edit()메서드 호출 전에 배열을 우리가 입력한 데이터를 반영하여 조작을 가하자
				member[1]=txt_name.getText();
				member[2]=txt_tel.getText();
				
				edit(member);//조작한 일차원 배열 member 넘기기
				//selectAll(); 좋은 방향. 일단 성공했을 때 나올 수 있게 edit()메서드에서 호출
			}
		}else if(obj==bt_del) { // 삭제라면
			int result=JOptionPane.showConfirmDialog(this, "삭제하시겠어요?"); //alert창의 부모 컴포넌트는 this윈도우창
			if(result==JOptionPane.OK_OPTION) {
				delete(member4_id);//pk를 넘겨줘야함
				selectAll();
			}
		}
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		System.out.println("windowOpened()");
	}

	//윈도우 창을 닫는 순간 호출되는 메서드.
	//연결되어있던 자원을 해제하는 용도로 적합
	//여기서  데이터베이스 끊기
	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("windowClosing()");
		//데이터 베이스 접속 끊기
		try {
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		//프로세스 종료 (실행중인 프로그램 = 프로세스)
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		System.out.println("windowClosed()");
	}

	@Override
	public void windowIconified(WindowEvent e) {
		System.out.println("windowIconified()");
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		System.out.println("windowDeiconified()");
	}

	@Override
	public void windowActivated(WindowEvent e) {
		System.out.println("windowActivated()");	
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		System.out.println("windowDeactivated()");
	}

	//MouseListener 메서드 재정의
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {
		//테이블을 마우스로 클릭시, JTable의 메서드 중 유저가 선택한 row, col정보를 반환하는 메서드
		int row=table.getSelectedRow(); //유저가 선택한 층(row)
		int col=table.getSelectedColumn();
		
		String[][] rows=((MyModel)model).rows;
		//방법1)
		//System.out.println("선택한 레코드의 이름: "+rows[row][1]);
		System.out.println("선택한 레코드의 PK: "+rows[row][3]); //"4"로 나오니까 ""를 날려야함 (Wrapper클래스 사용)
		member=rows[row]; //멤버변수로 보관
		
		//방법2) JTable자체에 getValueAt메서드 있음 (반환형 Object)
		//String value=(String) table.getValueAt(row, col); //다운캐스팅도 가능
		//System.out.println(value);
		
		//아래의 코드는 이미 member정보를 보관한 일차원 배열에 의해 불필요한 코드이지만
		//이미 삭제하기를 구현할 때 사용했으므로 그냥 냅둔다
		//사용자 상세정보 보기
		member4_id=Integer.parseInt(rows[row][3]); //멤버변수에 보관
		select(member4_id);
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}
