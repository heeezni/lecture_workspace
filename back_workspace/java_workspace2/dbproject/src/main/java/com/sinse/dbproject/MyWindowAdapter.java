package com.sinse.dbproject;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/*KeyListener, WindowListener 등 재정의 할 메서드가 3개 이상 되는 인터페이스의 경우
 * 개발자 대신 이미 java api차원에서, 즉 우리 대신 리스너 인터페이스를 구현해 놓은 
 * 중간 객체들을 가리켜 어댑터라고 한다.
 * like 전자제품과 220v 사이의 완충 장치 역할
 * 우리 대신 메서드들을 재정의해놓았기 때문에 개발자는 구현의무가 없게 됨*/

public class MyWindowAdapter extends WindowAdapter{

	public void windowClosing(WindowEvent e) {
		System.out.println("창 닫았어?");
	}
}
