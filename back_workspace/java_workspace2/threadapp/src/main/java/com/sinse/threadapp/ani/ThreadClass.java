package com.sinse.threadapp.ani;

import javax.swing.JLabel;

public class ThreadClass extends Thread{
	
	JLabel label;
	int n;
	int x;
	
	public ThreadClass(JLabel label, int x) {
		this.label=label;
		this.x=x;
		
	}
	@Override
	public void run() {
		while(true) {
			try {
				n+=x;
				label.setText(Integer.toString(n));
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

}
