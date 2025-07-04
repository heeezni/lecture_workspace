package com.sinse.threadapp.ani;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

/*스윙의 UI컴포넌트 중 진행률을 표현하는 컴포넌트인 JProgressBar를 사용해보자*/
public class ProgressTest2 extends JFrame {

	JButton bt;
	JProgressBar[] bar=new JProgressBar[3];


	// 독립적으로 실행할 단위인 스레드 3개 준비
	ProgressThread t1;
	ProgressThread t2;
	ProgressThread t3;

	public ProgressTest2() {

		setLayout(new FlowLayout());

		for (int i = 0; i < bar.length; i++) {
			bar[i] = new JProgressBar();
			bar[i].setPreferredSize(new Dimension(750, 45));
			add(bar[i]);
		}

		bt = new JButton("진행");
		add(bt);
		add(bar[0]);
		add(bar[1]);
		add(bar[2]);

		bt.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				t1 = new ProgressThread(bar[0], 1);
				t2 = new ProgressThread(bar[1], 5);
				t3 = new ProgressThread(bar[2], 8);

				t1.start();
				t2.start();
				t3.start();
			}
		});

		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new ProgressTest2();
	}
}
