package shop.pages;
//쇼핑몰에서 모든 페이지들의 최상위 객체

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Page extends JPanel {
	JLabel la_title;
	
	public Page(String title) {
		la_title = new JLabel(title);
		la_title.setFont(new Font("굴림", Font.BOLD, 30));
		
		add(la_title);

		setPreferredSize(new Dimension(800,650)); //크기 지정하지 않으면 패널이 패킹됨 (pack)
	}
}
