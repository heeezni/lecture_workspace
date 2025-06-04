package com.sinse.threadapp.ani;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

//사각형을 이동시켜보자 (지우고 그리는 작업을 연속적으로 시도)
public class RectMove extends JFrame{
	JPanel p_north;
	JPanel p_center;
	JButton bt;
	int x;
	int y;
	Thread thread;
	
	public RectMove() {
		//생성
		p_north=new JPanel();
		
		p_center=new JPanel() {
			public void paint(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.fillRect(x, y , 50, 50);
			}
		};
		
		thread=new Thread() {
			public void run() { //스레드로 구현하고 싶은 코드를 run()메서드 안에 넣어두기
				super.run();
				while(true) { //무한루프 걸어주기
					move();
					try {
						Thread.sleep(10); //시간 조절 해주기
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		bt=new JButton("동작");
		
		//조립
		p_north.add(bt);
		add(p_north, BorderLayout.NORTH);
		add(p_center);
		
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thread.start();
			}
		});
		
		setSize(800,700);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void move() {
		x++;
		y++;
		p_center.repaint(); //update()싹 지우고, paint()호출
	}
	
	public static void main(String[] args) {
		new RectMove();
	}
}
