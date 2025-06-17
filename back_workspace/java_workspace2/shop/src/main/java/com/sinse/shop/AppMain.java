package com.sinse.shop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sinse.shop.common.config.Config;
import com.sinse.shop.common.view.Page;
import com.sinse.shop.home.MainPage;
import com.sinse.shop.member.view.Memberjoin;
import com.sinse.shop.product.view.ProductDetailPage;

public class AppMain extends JFrame{
	JPanel p_north; //p_util, p_navi 공존시켜야 하므로
	JPanel p_util; 	//최상단 유틸리티, 네비 영역 (서브메뉴)
	JPanel p_navi; //메인 네비게이션
	JPanel p_container; 	//모든 페이지가 전환될 컨테이너 영역
	
	//유틸리티 네비 관련
	JLabel la_login;
	JLabel la_join;
	JLabel la_cart;
	JLabel la_wishlist;
	
	//메인 네비게이션 관련
	JLabel la_home;
	JLabel la_category;
	JLabel la_new;
	JLabel la_best;
	JLabel la_cs;
	
	Page[] pages;
	
	public AppMain() {
		//생성
		p_north=new JPanel(new FlowLayout(FlowLayout.LEFT,0,0)); //"왼쪽부터(처음부터) 시작해서 상하좌우 마진 0이다."
		p_util=new JPanel(new FlowLayout(FlowLayout.RIGHT)); //패널 내에서의 우측 정렬
		p_navi=new JPanel();
		p_container=new JPanel();
		
		la_login=new JLabel("Login");
		la_join=new JLabel("Join");
		la_cart=new JLabel("Cart");
		la_wishlist=new JLabel("WishList");
		
		la_home=new JLabel("Home");
		la_category=new JLabel("Category");
		la_new=new JLabel("New Arrivals");
		la_best=new JLabel("Best");
		la_cs=new JLabel("CS");
		
		
		//스타일
		p_util.setBackground(Color.ORANGE);
		p_navi.setBackground(Color.PINK);
		p_container.setBackground(Color.LIGHT_GRAY);
		
		p_north.setPreferredSize(new Dimension(Config.SHOPMAIN_WIDTH, Config.UTIL_HEIGHT+Config.NAVI_HEIGTH));
		p_util.setPreferredSize(new Dimension(Config.SHOPMAIN_WIDTH, Config.UTIL_HEIGHT));
		p_navi.setPreferredSize(new Dimension(Config.SHOPMAIN_WIDTH, Config.NAVI_HEIGTH));
		p_container.setPreferredSize(new Dimension(Config.SHOPMAIN_WIDTH, 810));
		
		
		//조립
		p_util.add(la_login);
		p_util.add(la_join);
		p_util.add(la_cart);
		p_util.add(la_wishlist);
		
		p_navi.add(la_home);
		p_navi.add(la_category);
		p_navi.add(la_new);
		p_navi.add(la_best);
		p_navi.add(la_cs);
		
		p_north.add(p_util);
		p_north.add(p_navi);
		
		add(p_north, BorderLayout.NORTH);
		add(p_container);
		
		createPage();//앱이 가동될 때 모든 페이지 생성 및 부착
		//그 부착된 페이지들 중 보고 싶은 페이지의 index를 넘기자
		showPage(Config.MAIN_PAGE);
		
		la_join.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				showPage(Config.JOIN_PAGE);
			}
			
			
		});
		
		
		setBounds(50,50,1415,900);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE); //db 연동 후엔 지우기
	}
	
	//쇼핑몰의 모든 페이지를 생성하여 부착!
	public void createPage() {
		//페이지들 담을 배열생성(반복문 돌릴거니까)
		pages=new Page[3]; //본인이 만든 페이지 수로 대체
		
		//페이지 생성
		pages[0]=new MainPage(this);
		pages[1]=new Memberjoin(this);
		pages[2]=new ProductDetailPage(this);
		
		for(int i=0; i<pages.length; i++) {
			p_container.add(pages[i]);
		}
	}
	
	//원하는 페이지를 보여주는 메서드 정의
	public void showPage(int target) {
		//반복문의 대상이 되려면 모든 페이지는 같은 자료형의 배열로 존재해야 함
		//그래서 'Page'라는 최상위 객체를 만들었음
		
		for(int i=0; i<pages.length; i++) {
			pages[i].setVisible((i==target)? true:false); //반복문 돌린거랑 target이랑 같으면 true 아니면 false
		}
	}
	
	public static void main(String[] args) {
		new AppMain();
	}
}
