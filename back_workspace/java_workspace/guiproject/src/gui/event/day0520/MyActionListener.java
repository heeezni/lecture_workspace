package gui.event.day0520;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;

public class MyActionListener implements ActionListener{
	Button bt_1;
	Button bt_2; //기존 윈도우의 버튼 원함
	
	//생성자를 통해 다른 클래스에 존재하던 버튼들을 전달받음(injection)
	public MyActionListener (Button bt_1, Button bt_2){
		this.bt_1=bt_1; //this는 MyActionListener
		this.bt_2=bt_2;
		//메서드 인젝션 / 생성자 인젝션
	}
	
	/*사용자가 액션 이벤트를 일으키면 OS로부터 이벤트 정보를 받은 JVM은 
	해당 이벤트 정보를 알맞은 이벤트 객체로 인스턴스화 시킴
	그리고 생성된 이벤트 인스턴스는, 재정의 메서드인 actionPerformed()메서드로 전달
	개발자는 자신이 알고싶은 이벤트 정보를 ActionEvent로부터 모든 것을 알아낼 수 있다.
	JS에서의 addEventListener("click", (e)=>{});의 e와 동일*/
	public void actionPerformed(ActionEvent e){
		//이벤트를 일으킨 주체를 가리켜 이벤트 소스 (Event Source)라고 함
		Object obj=e.getSource(); //obj는 윗 서랍장을 가리키는 Button
		//System.out.println("액션 일으킨 주체는 "+ obj);
		
		if(obj==bt_1){
			System.out.println("A를 눌렀어?");
		}else if(obj==bt_2){
			System.out.println("B를 눌렀어?");
		}
	}
}
