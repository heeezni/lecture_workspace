package com.sinse.shopadmin.security;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.sinse.shopadmin.AppMain;
import com.sinse.shopadmin.security.model.Admin;

public class LoginForm extends JFrame{
	JLabel la_id;
	JLabel la_pwd;
	JTextField t_id;
	JPasswordField t_pwd;
	JButton bt_login;
	JButton bt_join;

	Connection con;
	String url="jdbc:mysql://localhost:3306/shop";
	String id="shop";
	String pwd="1234";


	public LoginForm() {
		//생성
		la_id=new JLabel("ID");
		la_pwd=new JLabel("Password");
		t_id=new JTextField();
		t_pwd=new JPasswordField();
		bt_login=new JButton("Login");
		bt_join=new JButton("Join");

		//스타일
		Dimension d=new Dimension(110,30);
		la_id.setPreferredSize(d);
		t_id.setPreferredSize(d);
		la_pwd.setPreferredSize(d);
		t_pwd.setPreferredSize(d);

		//조립
		setLayout(new FlowLayout());
		add(la_id);
		add(t_id);
		add(la_pwd);
		add(t_pwd);
		add(bt_login);
		add(bt_join);

		connect();

		bt_login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginCheck();
			}
		});

		setBounds(300,300,270,145);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}


	//DB연결
	public void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,id,pwd);
			if(con!=null) {
				this.setTitle("접속 중");
			}else {
				this.setTitle("접속 에러");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//admin인지 체크
	public void loginCheck() {
		String id=t_id.getText();//일반 텍스트 컴포넌트의 스트링 값 얻기
		String pwd=new String(t_pwd.getPassword()); //String 명시적 생성법도 쓴다!

		String sql="select * from admin where id=? and pwd=?"; //✅ ?=바인드 변수
		PreparedStatement pstmt=null;
		ResultSet rs=null;

		try {
			pstmt=con.prepareStatement(sql);
			//쿼리문을 수행하기 위해, 바인드 변수 먼저 지정하기!
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs=pstmt.executeQuery(); //select 문은 표를 반환한다

			if(rs.next()) { //한 칸 전진 후 true가 반환된다면 일치하는 데이터가 있다는 것이고, 일치하는 데이터가 있다는 것은 로그인 성공!
				JOptionPane.showMessageDialog(this, "로그인 성공");
				
				//로그인 성공한 사람의 정보 담기
				Admin admin=new Admin(); //empty상태의 객체
				admin.setAdmin_id(rs.getInt("admin_id"));
				admin.setId(rs.getString("id"));
				admin.setPwd(rs.getString("pwd"));
				admin.setName(rs.getString("name")); //rs는 finally에서 close하기 때문에 다른 곳에 담아놔야함
				
				AppMain appMain=new AppMain(con, admin); //admin 로그인 시 관리자 창 띄우기
				this.setVisible(false); //프로세스 죽이면(System.exit(0) AppMain도 다 끝남
			}else {
				JOptionPane.showMessageDialog(this, "로그인 실패");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
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

		public static void main(String[] args) {
			new LoginForm();
		}
	}
