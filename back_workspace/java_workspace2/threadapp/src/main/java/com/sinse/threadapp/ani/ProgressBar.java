package com.sinse.threadapp.ani;

import javax.swing.JProgressBar;

public class ProgressBar extends Thread {

	JProgressBar bar;
	int delay;
	int step;

	public ProgressBar(JProgressBar bar, int delay, int step) {
		this.bar = bar;
		this.delay = delay;
		this.step = step;
	}

	public void run() {

		for (int i = 0; i <= 100; i += step) {
			bar.setValue(i);
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

		}
	}
}
