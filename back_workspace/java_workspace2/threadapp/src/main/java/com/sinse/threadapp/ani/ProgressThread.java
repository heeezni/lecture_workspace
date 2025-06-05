package com.sinse.threadapp.ani;

import javax.swing.JProgressBar;

public class ProgressThread extends Thread{
	JProgressBar bar;
	int n;
	int velX;
	
	public ProgressThread(JProgressBar bar, int velX) {
		this.bar=bar;
		this.velX=velX;
	}
	
	public void run() {
		while(n<=100) { //진행바가 100에 도달하면 종료	
			try {
				Thread.sleep(10);
				bar.setValue(n);
				n+=velX;
			} catch (InterruptedException e) {
				e.printStackTrace();
				break; //예외발생 시 스레드 종료
			}
		}
	}
}
