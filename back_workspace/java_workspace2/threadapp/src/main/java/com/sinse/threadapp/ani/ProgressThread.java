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
		while(true) { //진행바가 100에 도달하면 종료	
			try {
				Thread.sleep(10);
				//if (n > 100) break; 
	            //if를 앞에 둘 거면 n > 100으로 해야 100까지 보여주고 멈출 수 있음
				n+=velX;
				bar.setValue(n);
				if (n >= 100) break; // 100을 넘으면 루프 종료 (종료 조건)
			} catch (InterruptedException e) {
				e.printStackTrace();
				break; //예외발생 시 스레드 종료
			}
		}
	}
}
