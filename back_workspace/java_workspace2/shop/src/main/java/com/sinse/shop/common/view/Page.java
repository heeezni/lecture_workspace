package com.sinse.shop.common.view;

import javax.swing.JPanel;

import com.sinse.shop.AppMain;

/*쇼핑몰의 모든 페이지의 최상위 객체
 * 이 객체가 JPanel을 상속*/
public class Page extends JPanel{
	public AppMain appMain; //누구나 접근할 수 있게 public으로 풀어주자
	
	public Page(AppMain appMain) {
		this.appMain=appMain;
		setVisible(false);
	}
}
