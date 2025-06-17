package com.sinse.shop.product.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.sinse.shop.AppMain;
import com.sinse.shop.common.config.Config;
import com.sinse.shop.common.view.Page;

public class ProductDetailPage extends Page{
	
	JPanel p_wrapper; //실제 컨텐츠 크기
	JPanel p_img; //좌측 큰 이미지와 썸네일을 감쌀 패널
	JPanel p_detail; //상품 정보 감쌀 패널
	JPanel p_big; //큰 이미지 패널
	
	public ProductDetailPage(AppMain appmain) {
		super(appmain);
		
		//생성
		p_wrapper= new JPanel();
		p_img=new JPanel();
		p_detail=new JPanel();
		p_big=new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.RED);
				g.fillRect(0, 0, this.getPreferredSize().getSize().width, this.getPreferredSize().getSize().height);
				//g.drawImage(null, ALLBITS, ABORT, WIDTH, HEIGHT, appmain);
				
				
			}
		};
		
		//스타일
		p_img.setBackground(Color.PINK);
		p_detail.setBackground(Color.LIGHT_GRAY);
		p_wrapper.setPreferredSize(new Dimension(Config.SHOPMAIN_WIDTH-300,Config.SHOPMAIN_HEIGHT-Config.UTIL_HEIGHT-Config.NAVI_HEIGTH));
		p_img.setPreferredSize(new Dimension(p_wrapper.getPreferredSize().getSize().width/2-5, 450));
		p_detail.setPreferredSize(new Dimension(p_wrapper.getPreferredSize().getSize().width/2-5, 450));
		p_big.setPreferredSize(new Dimension(p_img.getPreferredSize().getSize().width-20, p_img.getPreferredSize().getSize().height-80));
		
		//조립
		p_img.add(p_big);
		p_wrapper.add(p_img);
		p_wrapper.add(p_detail);
		add(p_wrapper);
		
		setPreferredSize(new Dimension(Config.SHOPMAIN_WIDTH, Config.SHOPMAIN_HEIGHT-Config.UTIL_HEIGHT-Config.NAVI_HEIGTH));
		setBackground(Color.BLACK);
		setVisible(true);
	}

}
