package shop;
/*화면을 4개 보유한 쇼핑몰 애플리케이션의 화면 전환 처리방법*/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import shop.pages.CS;
import shop.pages.Home;
import shop.pages.MyPage;
import shop.pages.Page;
import shop.pages.Product;
import util.ImageUtil;

public class ShopMain extends JFrame implements ActionListener{
	JPanel p_north;
	JButton bt_home;
	JButton bt_product;
	JButton bt_mypage;
	JButton bt_cs;
	
	JPanel container; //여러 페이지들의 전환 시, 바깥쪽 컨테이너 역할
	//프로그램 가동과 동시에 이 패널에는 앞으로 사용하게 될 페이지들을 미리 부착
	
	/*
	 * URI: 자원을 표현하는 방법
	 * URL: URI 중에서 해당 자원의 위치를 표현한다면...
	 * */
	ImageUtil imageUtil;
	
	/*쇼핑몰을 구성하는 모든 페이지를 보유한다*/
	Page[] pageArray = new Page[4];
	
	/*Home home;
	MyPage mypage;
	CS cs;
	Product product;*/
	
	//상수로 정의
	public static final int HOME=0;
	public static final int PRODUCT=1;
	public static final int MYPAGE=2;
	public static final int CS=3;
	
	public ShopMain() {
		imageUtil = new ImageUtil();
		
		//생성
		p_north = new JPanel();
		bt_home = new JButton(imageUtil.getIcon("home.png", 30, 30));
		bt_product = new JButton(imageUtil.getIcon("product.png", 30, 30));
		bt_mypage = new JButton(imageUtil.getIcon("mypage.png", 30, 30));
		bt_cs = new JButton(imageUtil.getIcon("cs.png", 30, 30));
		container = new JPanel();
		
		/*home=new Home();
		mypage=new MyPage();
		cs=new CS();
		product=new Product();*/
		
		pageArray[0] = new Home();
		pageArray[1] = new Product();
		pageArray[2] = new MyPage();
		pageArray[3] = new CS();
		
		//스타일
		p_north.setPreferredSize(new Dimension(800,50));
		p_north.setBackground(Color.pink);
		Dimension d = new Dimension(40,35);
		bt_home.setPreferredSize(d);
		bt_product.setPreferredSize(d);
		bt_mypage.setPreferredSize(d);
		bt_cs.setPreferredSize(d);
		
		//개발자가 버튼에 추가적인 값을 심을 수 있다
		bt_home.putClientProperty("id", HOME);
		bt_product.putClientProperty("id", PRODUCT);
		bt_mypage.putClientProperty("id", MYPAGE);
		bt_cs.putClientProperty("id", CS);
		
		
		//조립
		p_north.add(bt_home);
		p_north.add(bt_product);
		p_north.add(bt_mypage);
		p_north.add(bt_cs);
		
		for(int i=0; i<pageArray.length; i++) {
			container.add(pageArray[i]); //페이지에 부착
			pageArray[i].setVisible(false); //모두 숨김
		}
		pageArray[HOME].setVisible(true); //처음에 HOME만 보이게

		
		add(p_north, BorderLayout.NORTH);
		add(container); //프레임의 센터에 패널 부착
		
		bt_home.addActionListener(this);
		bt_product.addActionListener(this);
		bt_mypage.addActionListener(this);
		bt_cs.addActionListener(this);
		
		setSize(800,650);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null); //JS의 margin: auto
	}
	
	// 원하는 페이지만 보여지게 처리하는 메서드
	public void showPage(int target) { //보여지길 원하는 페이지의 인덱스를 호출 시 결정
		//반복문 처리 하려면 같은 자료형으로 묶어야함
		
		for(int i=0; i<pageArray.length; i++) {
			/*if(i==target) {
				pageArray[target].setVisible(true);				
			} else {
				pageArray[i].setVisible(false);				
			}*/
			pageArray[i].setVisible((i==target)? true : false);				
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {	
		JButton obj=(JButton)e.getSource(); //버튼으로 다운캐스팅
		
		int id = (int)obj.getClientProperty("id"); //눌린 버튼 마다 자신의 id 값인 0,1,2,3
		System.out.println("당신이 누른 버튼이 가진 상수 값은"+id);
		showPage(id);
	}
	
	public static void main(String[] args) {
		new ShopMain();
	}
}
