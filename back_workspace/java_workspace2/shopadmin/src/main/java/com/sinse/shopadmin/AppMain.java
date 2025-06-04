package com.sinse.shopadmin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sinse.shopadmin.common.config.Config;
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
	
	Connection con;
	Admin admin;

	public AppMain(Connection con, Admin admin) {
		//넘겨받았으면 얼른 멤버변수에 보관
		this.con=con;
		this.admin=admin;
		
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
		p_side.setBackground(Color.YELLOW);
		
		//조립
		add(p_banner, BorderLayout.NORTH);
		add(p_side, BorderLayout.WEST);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);//DB연동 후 제거
		setBounds(50,50,Config.ADMINMAIN_WIDTH, Config.ADMINMAIN_HEIGHT);
		setVisible(true);
		
		
	}

	/*public static void main(String[] args) {
		new AppMain();
	}*/ //관리자 화면은 로그인 후 관리자 인증이 됐을 때 나오는 화면이니까  
}
