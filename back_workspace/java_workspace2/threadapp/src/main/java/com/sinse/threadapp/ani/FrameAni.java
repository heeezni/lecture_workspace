package com.sinse.threadapp.ani;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrameAni extends JFrame{
	JPanel p_center;
	Image[] images=new Image[18];
	int n;
	Thread thread;
	
	public FrameAni() {
		createImage();//패널이 그림을 그리기 전에 배열을 완성해야 하므로
		
		p_center=new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(images[n], 100, 100, 200, 213, this);
			}
		};
		
		thread=new Thread() {
			public void run() {
				super.run();
				while(true) {
					try {
						Thread.sleep(10);
						n++;
						if(n>=images.length) n=0; // 맨 처음 images[0]이 나와야함
						
						
						p_center.repaint();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		thread.start(); //JVM에게 runnable 상태로의 진입 부탁
		
		add(p_center);
		
		setSize(500,500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	
	public void createImage() {
		
		for(int i=0; i<images.length; i++) {
			URL url=this.getClass().getClassLoader().getResource("hero/image"+(i+1)+".png"); //파일명은 1부터
			
			try {
				BufferedImage buffrImg=ImageIO.read(url);
				Image img=buffrImg.getScaledInstance(213, 200, Image.SCALE_SMOOTH);
				images[i]=img; //배열의 i번째 요소로 이미지 넣기
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
				
	}
		
	public static void main(String[] args) {
		new FrameAni();
	}

}
