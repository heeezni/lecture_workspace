package com.sinse.shop.product.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.sinse.shop.common.util.StringUtil;
import com.sinse.shop.product.model.Product;

// 상품하나를 표현하는 디자인 카드
public class ProductItem extends JPanel {
	Product product;
	Image img;
	
	public ProductItem(Product prodcut) {
		this.product = prodcut;
		
		// Product에 들어있는 이미지 가져오기
		try {
			img=ImageIO.read(new File("C:/public/"+product.getFilenameList().get(0)));
			img=img.getScaledInstance(210, 150, Image.SCALE_SMOOTH);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		setPreferredSize(new Dimension(220, 280));
		setBackground(Color.YELLOW);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g; // g가 업그레이드 된 버전
		g2.drawImage(img, 5, 5, 210, 150, this);
		
		//상품명 그리기
		g2.setFont(new Font("GUlim", Font.BOLD,20));
		g2.drawString(product.getProduct_name(), 5, 180); //상품명
		
		g2.drawString(StringUtil.getPriceString(product.getPrice())+"원", 5, 205);
	
	}
}
