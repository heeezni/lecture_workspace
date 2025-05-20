package gui.event;
import java.awt.Frame;
import java.awt.Button;
import java.awt.FlowLayout;
import gui.event.day0520.MyActionListener;

public class DoubleButton {
	public static void main(String[] args) {
		Frame frame = new Frame();
		Button bt_1=null, bt_2=null;
		
		bt_1=new Button("A");
		bt_2=new Button("B");
	
		frame.setLayout(new FlowLayout());//플로우 배치 적용
		frame.setSize(300,400);

		frame.add(bt_1);
		frame.add(bt_2);
		
		MyActionListener my=new MyActionListener(bt_1,bt_2);
		//my.setBtn(bt_1,bt_2); //버튼 1,2의 주소값을 setBtn()으로 넘김 
		
		bt_1.addActionListener(my);//버튼1과 리스너 연결
		bt_2.addActionListener(my);//버튼2와 리스너 연결
			
		frame.setVisible(true);
	}
}