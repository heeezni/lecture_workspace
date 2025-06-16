/*Java GUI에서도 사용자의 반응에 대한 이벤트 처리가 가능함
하지만 Js에서의 처리보다 훨씬 복잡함
html에서 클릭이벤트는 click
Java에서는 클릭이벤트라는 용어자체가 X, action에 소속*/
package gui.event;

import java.awt.Frame;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.Choice;

public class ActionTest{
	public static void main(String[] args) {
		Frame frame=null;
		Button bt=null;
		TextField txt=null;
		Choice ch=null; //html에서의 select박스
		
		frame=new Frame();
		bt=new Button("click me!");
		txt=new TextField(20);
		ch=new Choice();
		ch.addItem("Choose your mail server");
		ch.addItem("@naver.com");//html에서의 <option>
		ch.addItem("@daum.net");
		ch.addItem("@gmail.com");
		
		//Js에서 처럼 bt.addEventListener()메서드를 버튼에 연결하는 과정을 동일하게 진행
		bt.addActionListener(new MyActionListener()); //이벤트를 구현한 객체의 인스턴스 →내가 만든 클래스
		txt.addKeyListener(new MyKeyListener()); //텍스트 필드와 리스너와의 연결 (우리가 구현한 키리스너 객체 넣기)
		ch.addItemListener(new MyItemListener());
		frame.addMouseListener(new MyMouseListener());
		
		frame.setLayout(new FlowLayout()); //대왕버튼 만들어지지 않게 Flow
		frame.add(bt);
		frame.add(txt);
		frame.add(ch);
		frame.setSize(300,400);
		frame.setVisible(true);
	}
}
