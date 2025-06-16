/*
AWT는 자바의 초창기 GUI 패키지임은 맞지만,
os마다 다른 디자인으로 실행됨 (mad-맥에 맞게, window-윈도우에 맞게)
swing은 os(=플랫폼)에 상관없는 중립적인 Look&Feel 디자인 유지
요즘은 swing처럼 os에 어울리지 않는 java에 최적화 디자인을 지양함 → javaFX

swing은 기존의 awt를 계승했기 때문에 우리가 사용했던 awt컴포넌트명에 J접두어만 붙는다
*/
package gui.swing;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class  MyWin extends JFrame implements ActionListener{
	JTextArea area;
	JPanel p_south;
	JButton bt;

	public MyWin(){
		area=new JTextArea(4,15); //행 열 (4줄의 15칸)
		p_south=new JPanel();
		bt=new JButton("환경설정");
		
		//area에 배경색상
		area.setBackground(Color.YELLOW);
		
		add(area);
		p_south.add(bt);
		add(p_south, BorderLayout.SOUTH);
		
		bt.addActionListener(this);
		
		//setSize(300,400);
		setBounds(500,100,300,400); //x,y,width,height
		setVisible(true);
	}
	
	//리스너 메서드 오버라이딩
	public void actionPerformed(ActionEvent e){
		new ConFig(this); //this :인스턴스가 자기 자신을 가리키는 레퍼런스 변수명
	}
	
	public static void main(String[] args) {
		new MyWin();
	}
}
