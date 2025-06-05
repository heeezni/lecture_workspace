package com.sinse.threadapp.gallery;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Gallery extends JFrame{
	JPanel p_west; // 썸네일이 그려질 통 패널(모든 썸네일을 그려서 표현)
	JPanel container; //북쪽, 큰 사진 패널들을 감싸안을 영역 (BorderLayout)
	JPanel p_north; // 북쪽 컨트롤러 영역
	JPanel p_center; // 큰 사진 보여질 영역
	
	ImageUtil imageUtil=new ImageUtil();
	Image[] images=new Image[9];
	
	/*화면에 렌더링하지는 않지만, 원하는 좌표에 사각형을 메모리상에만 존재시키면
	 * 원하는 영역에 이벤트를 부여할 수 있다*/
	Rectangle[] rects=new Rectangle[images.length];
	float y=10f;
	Thread thread; //JS에서의 그 게임루프 (setInterval)
	float a=0.1f; //비율계수
	int targetY;
	int currentIndex; //현재 유저가 클릭한 바로 그 이미지 인덱스(i)
	public Gallery() {
		
		createImage(); //그려지기 전에 이미지 준비해놓아야 한다
		thread=new Thread() {
			public void run() {
				while(true) {
					try {
						Thread.sleep(10);
						
						move();
						
						p_west.repaint();
					} catch (InterruptedException e) {
						e.printStackTrace();
						break;
					}
				}
			}
		};
		thread.start();
		
		p_west=new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g); //배경을 스스로 채워야 함
				
				for(int i=0; i<images.length; i++) {
					g.drawImage(images[i], 5, 10+(95*i), 90, 90, this);
				}
				//그려진 이미지 위로 옮겨다닐 사각 포인터
				Graphics2D g2=(Graphics2D)g;
				g2.setStroke(new BasicStroke(5));
				g.setColor(Color.RED);
				g.drawRect(5, (int)y, 90, 90);
			}
		};
		container=new JPanel();
		p_north=new JPanel();
		p_center=new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(images[currentIndex] ,0, 0, 800, 850, this);
			}
		};
		
		//스타일
		p_west.setBorder(new LineBorder(Color.lightGray));
		p_west.setPreferredSize(new Dimension(100,800));
		
		add(p_west, BorderLayout.WEST);
		container.setLayout(new BorderLayout());
		container.add(p_north, BorderLayout.NORTH);
		container.add(p_center);
		add(container);
		
		//좌측 패널에 마우스 이벤트 연결
		p_west.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				for(int i=0; i<rects.length;i++) {
					if(rects[i].contains(e.getPoint())) {
						currentIndex=i;
						p_center.repaint();
						
						targetY=rects[i].y;
					}
				}
			}
		});
		targetY=10;
		
		
		setSize(900,900);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void createImage() {
		for(int i=0; i<images.length; i++) {
			images[i]=imageUtil.getImage("geographic/animal"+(i+1)+".jpg", 90, 90);
			rects[i]=new Rectangle(5,10+(95*i),90,90);
		}
	}
	
	//포인터가 목적지에 한 번에 도달하게 하지 말고, 부드럽게 움직일 수 있게 **감속도 공식** 적용
	public void move() {
		//y=현재 y+a*(목적 y지점-현재 y지점)
		y=y+a*(targetY-y);
	}
	
	public static void main(String[] args) {
		new Gallery();
	}
}
