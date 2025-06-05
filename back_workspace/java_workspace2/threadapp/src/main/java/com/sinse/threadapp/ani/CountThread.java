package com.sinse.threadapp.ani;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CountThread extends JFrame{
	
	JPanel p_north;
	JButton bt;
	JPanel p_center;
	JLabel count1;
	JLabel count2;

	ThreadClass t1;
	ThreadClass t2;
	
	
	public CountThread() {
		p_north=new JPanel();
		bt=new JButton("start");
		p_center=new JPanel();
		count1=new JLabel();
		count2=new JLabel();
		
		bt.setBackground(Color.RED);
		Dimension d=new Dimension(180,180);
		count1.setPreferredSize(d);
		count2.setPreferredSize(d);
		
		p_north.add(bt);
		p_center.add(count1);
		p_center.add(count2);
		
		add(p_north, BorderLayout.NORTH);
		add(p_center);
		
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t1=new ThreadClass(count1, 2);
				t2=new ThreadClass(count2, 1);
				
				t1.start();
				t2.start();
			}
		});
		
		setSize(600,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new CountThread();
	}
}
