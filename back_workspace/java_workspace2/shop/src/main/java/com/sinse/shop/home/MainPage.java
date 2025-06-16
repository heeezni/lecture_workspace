package com.sinse.shop.home;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.JPanel;

import com.sinse.shop.AppMain;
import com.sinse.shop.common.config.Config;
import com.sinse.shop.common.util.ImageUtil;
import com.sinse.shop.common.view.Page;
import com.sinse.shop.product.model.Product;
import com.sinse.shop.product.repository.ProductDAO;
import com.sinse.shop.product.view.ProductItem;

public class MainPage extends Page {
	JPanel p_visual; // 메인 비주얼 영역 (메인 배너 / carousel영역)
	JPanel p_content; // 상품이 출력될 영역
	ImageUtil imageUtil = new ImageUtil();
	Image img;
	ProductDAO productDAO=new ProductDAO();

	public MainPage(AppMain appMain) {
		super(appMain);
		// 생성
		img = imageUtil.getImage("images/패션.jpg", Config.MAIN_VISUAL_WIDTH, Config.MAIN_VISUAL_HEIGHT);
		p_visual = new JPanel() { // 패널을 이름없는 익명클래스로 재정의하는 코드를 작성
			// .java파일 생성 필요x, 내부클래스다 보니 외부 클래스인 MainPage멤버 공유 가능
			@Override
			protected void paintComponent(Graphics g) { // 성능 좋음 (더블버퍼링)
				super.paintComponent(g); // 남겨놔야 update()에 지워진 배경을 스스로 복구

				// 우리가 원하는 그림을 그리자 (패널의 그림을 뺏어 그리기)
				g.drawImage(img, 0, 0, Config.MAIN_VISUAL_WIDTH, Config.MAIN_VISUAL_HEIGHT, p_visual); // this로 써도됨
				// 어떤이미지,x,y,너비,높이, 옵저버(이미지를 포함하려는 영역넣으면 됨)
			}
		};

		p_content = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 15));

		// 스타일
		p_visual.setPreferredSize(new Dimension(Config.MAIN_VISUAL_WIDTH, Config.MAIN_VISUAL_HEIGHT));
		p_content.setPreferredSize(new Dimension(Config.MAIN_VISUAL_WIDTH, 410));
		setPreferredSize(new Dimension(Config.SHOPMAIN_WIDTH, (Config.SHOPMAIN_HEIGHT - Config.UTIL_HEIGHT)));

		p_visual.setBackground(Color.CYAN);
		p_content.setBackground(Color.RED);

		// 최신 상품 생성하기
		createRecentList();

		// 조립
		add(p_visual);
		add(p_content);

		setVisible(true);
	}

	// 최신 상품 패널 원하는 수만큼 p_content에 출력
	public void createRecentList() {
		List<Product>productList=productDAO.selectRecentList(1);
		
		for (int i = 0; i < productList.size(); i++) {
			Product product  = productList.get(i); //리스트에서 상품 하나씩 꺼내자!
			for(int a =0; a<product.getFilenameList().size(); a++) {
				System.out.println(i+ "번 째 "+product.getFilenameList().get(a));
			}
			
			ProductItem productItem = new ProductItem(product); //상품 하나를 표현하는 디자인 카드
			p_content.add(productItem);
		}

	}
}
