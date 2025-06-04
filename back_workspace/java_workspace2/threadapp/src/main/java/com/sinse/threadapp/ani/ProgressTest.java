package com.sinse.threadapp.ani;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

/*스윙의 UI컴포넌트 중 진행률을 표현하는 컴포넌트인 JProgressBar를 사용해보자*/
public class ProgressTest extends JFrame{

	JProgressBar[] bar=new JProgressBar[3];

	JButton bt;
	
	public ProgressTest() {
		setLayout(new FlowLayout());
		
		for(int i=0; i<bar.length; i++) {
			bar[i]=new JProgressBar();
			bar[i].setPreferredSize(new Dimension(750,45));
			add(bar[i]);
		}

		bt=new JButton("진행");
		add(bt);
				
		bt.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				/*thread=new ProgressBar(ProgressTest.this, 100); //그냥 this 아님. 지금은 익명내부 클래스
				*thread.start(); //JVM에게 runnable한 상태로 들어가도록 맡기자*/
				new ProgressBar(bar[0], 20, 5).start();
				new ProgressBar(bar[1], 100, 10).start();
				new ProgressBar(bar[2], 50, 2).start();
				
			}
		});
		
		setSize(800,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
//	public void move() { 
//		//bar 증가
//		n+=2;
//		for(int i=0; i<bar.length; i++) {
//			bar[i].setValue(n);			
//		}
//
//	}
	
	public static void main(String[] args) {
		new ProgressTest();
	}
}
