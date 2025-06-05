package com.sinse.shopadmin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.sinse.shopadmin.common.config.Config;
import com.sinse.shopadmin.common.view.Page;
import com.sinse.shopadmin.config.view.ConfigPage;
import com.sinse.shopadmin.cs.view.CustomerPage;
import com.sinse.shopadmin.main.view.MainPage;
import com.sinse.shopadmin.member.view.MemberJoin;
import com.sinse.shopadmin.member.view.MemberPage;
import com.sinse.shopadmin.order.view.OrderPage;
import com.sinse.shopadmin.product.view.ProductPage;
import com.sinse.shopadmin.security.LoginForm;
import com.sinse.shopadmin.security.model.Admin;

public class AppMain extends JFrame{
	JPanel p_banner;
	JPanel p_side; //사이드 메뉴 영역
	JPanel p_container; //페이지가 교체될 영역
	JLabel la_user;
	JLabel la_product;
	JLabel la_order;
	JLabel la_member;
	JLabel la_cs;
	JLabel la_config;//설정
	
	public Connection con;
	
	public Admin admin;
	
	//모든 페이지를 담게될 배열
	Page[]pages;
	

	public AppMain() {		
		//생성
		p_banner=new JPanel();
		p_side=new JPanel();
		p_container=new JPanel();
		la_user=new JLabel("heeezni");
		
		la_product=new JLabel("상품관리");
		la_order=new JLabel("주문관리");
		la_member=new JLabel("회원관리");
		la_cs=new JLabel("고객센터");
		la_config=new JLabel("환경설정");
		
		//스타일
		p_banner.setPreferredSize(new Dimension(Config.UTIL_WIDTH, Config.UTIL_HEIGHT));
		p_banner.setBackground(Color.CYAN);
		p_side.setPreferredSize(new Dimension(Config.SIDE_WIDTH, Config.SIDE_HEIGTH));
		p_side.setBackground(Color.WHITE);
		p_container.setPreferredSize(new Dimension((Config.ADMINMAIN_WIDTH-Config.SIDE_WIDTH), (Config.ADMINMAIN_HEIGHT-Config.UTIL_HEIGHT)));
		p_container.setBackground(Color.PINK);
		Dimension d=new Dimension(185,120);
		la_product.setPreferredSize(d);
		la_order.setPreferredSize(d);
		la_member.setPreferredSize(d);
		la_cs.setPreferredSize(d);
		la_config.setPreferredSize(d);
		Font f=new Font(null, Font.BOLD, 25);
		la_product.setFont(f);
		la_order.setFont(f);
		la_member.setFont(f);
		la_cs.setFont(f);
		la_config.setFont(f);
		
		//조립
		add(p_banner, BorderLayout.NORTH);
		add(p_side, BorderLayout.WEST);
		add(p_container);
		p_side.add(la_product);
		p_side.add(la_order);
		p_side.add(la_member);
		p_side.add(la_cs);
		p_side.add(la_config);
		
		//데이터베이스 접속해놓기
		connect(); 
		
		//모든 버튼에 이벤트 연결
		//리스너 중 재정의할 메서드가 많은 경우 (3개 이상 되는 경우)는 어댑터 지원을 의심해보자
		//어댑터: 우리 대신 리스너의 메서드를 오버라이딩 해놓은 중간 객체
		la_product.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				showPage(Config.PRODUCT_PAGE);
			}
		});
		la_order.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				showPage(Config.ORDER_PAGE);
			}
		});
		la_member.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				showPage(Config.MEMBER_PAGE);
			}
		});
		la_cs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				showPage(Config.CUSTOMER_PAGE);
			}
		});
		la_config.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				showPage(Config.CONFIG_PAGE);
			}
		});
		
		this.addWindowStateListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				//데이터베이스 접속 끊기
				if(con!=null)
					try {
						con.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				
				//프로세스 종료
				System.exit(0);
			}
		});
		
		createPage();
		showPage(-1); //-1은 존재하지 않는 페이지
		
		setBounds(50,50,Config.ADMINMAIN_WIDTH, Config.ADMINMAIN_HEIGHT);
		setVisible(true);
	}
	
	//DB연결
	public void connect() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(Config.url, Config.id, Config.pwd);
			if(con!=null) {
				this.setTitle("MySQL 접속성공");
			}else {
				this.setTitle("MySQL 접속실패");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	//쇼핑몰에 사용할 모든 페이지 생성 및 부착
	public void createPage() {
		pages=new Page[8];
		pages[0]=new LoginForm(this);
		pages[1]=new MainPage(this);
		pages[2]=new ProductPage(this);
		pages[3]=new OrderPage(this);
		pages[4]=new MemberPage(this);
		pages[5]=new CustomerPage(this);
		pages[6]=new ConfigPage(this);
		pages[7]=new MemberJoin(this);
		
		for(int i=0; i<pages.length; i++) {
			p_container.add(pages[i]);
		}
	}
	
	//부착된 페이지들을 대상으로, 어떤 페이지를 보여줄 지를 결정하는 메서드
	public void showPage(int target) {
		
		//로그인 체크를 여기서 진행하자
		if(admin == null && target != -1 && target != Config.JOIN_PAGE 
				&& target != Config.LOGIN_PAGE) {
			JOptionPane.showMessageDialog(this, "로그인이 필요한 서비스입니다.");
			pages[Config.LOGIN_PAGE].setVisible(true);
			return;
		}
		
		for(int i=0; i<pages.length; i++) {
			pages[i].setVisible(i==target);
		}
	}

	public static void main(String[] args) {
		new AppMain();
	} //관리자 화면은 로그인 후 관리자 인증이 됐을 때 나오는 화면이니까  
}
