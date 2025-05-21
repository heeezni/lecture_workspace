package gui.graphic;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Color;
import java.awt.Dimension;

public class GPanel extends JPanel{
	Toolkit kit=Toolkit.getDefaultToolkit(); 
	Image image;
	
	public GPanel(){
		setBackground(Color.PINK);
		setPreferredSize(new Dimension(600,450));
		image=kit.getImage("C:/lecture_workspace/back_workspace/java_workspace/guiproject/res/geographic/animal1.png");
	}
	//p_center영역에 이미지 출력하기
	public void showImg(){}
}
