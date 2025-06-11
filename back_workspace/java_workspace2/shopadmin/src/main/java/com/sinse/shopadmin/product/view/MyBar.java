package com.sinse.shopadmin.product.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JProgressBar;
import javax.swing.border.TitledBorder;

// API자체적으로 지원해주는 진행바는 너무 단순하므로, 우리가 원하는 스타일의 바로 커스텀해보자!
public class MyBar extends JProgressBar implements Runnable {
	// Runnable 인터페이스 : run()추상메서드를 보유한 인터페이스

	FileInputStream fis; // 파일대상 바이트기반 입력 스트림
	FileOutputStream fos; // 파일대상 바이트기반 출력 스트림
	File origin;
	File dest;
	int n; // 읽어들인 횟수 (read한 횟수)

	public MyBar(File origin, File dest) { // 원본파일 넘겨받기 + 저장할 곳

		this.origin = origin;
		this.dest = dest;

		System.out.println("새롭게 생성될 파일의 디렉토리는" + dest.getParent());
		System.out.println("새롭게 생성될 파일명은" + dest.getName());
		try {
			fis = new FileInputStream(origin); // 원본파일 대상으로 입력스트림 생성
			fos = new FileOutputStream(dest); // 파일이 복사될 대상에 대한 출력스트림 생성
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		setPreferredSize(new Dimension(680, 50));
		setBorder(new TitledBorder("어쩌구.jpg -> 1234.jpg"));
		// 진행바 중앙에 텍스트 띄우기
		setStringPainted(true);
		setFont(new Font("Verdana", Font.BOLD, 17));
		setForeground(Color.PINK);
		this.setValue(30);
	}

	// 진행률을 표시하는 메서드
	public void showRate(int v) {
		// (읽어들인 용량 / 파일 총 용량) *100
		n+=v;
		this.setValue((int) (n * 100 / origin.length())); // int로 형변환 하여 소수점 떼기
	}

	// 느리게 읽는 방법
	public void copySlow() {
		while (true) {
			int data = -1;

			try {
				data = fis.read();// 1byte 읽기
				if (data == -1)
					break;
				n++; // break문을 만나지 않았다면 읽은 것이므로

				// 프로그래스바의 진행률을 표시하자 -> (읽어들인 용량/총 파일의 용량) * 100
				showRate(n);
				fos.write(data); // 1byte 쓰기

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 한바이트씩 읽어들이지 말고, 버퍼를 만들어 한꺼번에 읽어들이기
	public void copyFast() {
		
		n=0;
		
		byte[] buff = new byte[1024];
		int read = 0;

		// 버퍼가 1024의 용량을 갖더라도, 컴퓨터 상황 (네트워크, os상황, 디스크 등등)
		// 이러한 이유로 인해 1024개가 다 모이지 않을 경우도 있으므로..
		while (true) {
			try {
				read = fis.read(buff); // Max : 1024바이트가 모아지면 읽는다 -> 1KB
				if(read==-1) break;
				fos.write(buff, 0, read); // 뭘, 어디서부터, 어디까지 (buff.length 쓰지 않기. 꼭 1024개 채우지 못할 수도 있음)
				
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				showRate(read);
			
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		release();
	}

	// 파일에 생성된 스트림을 통해 읽고 내뱉자
	@Override
	public void run() {
		copyFast();

		System.out.println("복사완료");
		release();
	}

	public void release() {
		// 스트림은 닫아야 복사완료가 됨
		if (fos != null) {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (fis != null) {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
