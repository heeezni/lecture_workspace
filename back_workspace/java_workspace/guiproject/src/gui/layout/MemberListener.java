package gui.layout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;

public class MemberListener implements ActionListener{
	JoinForm joinForm;
	Button bt_login;
	Button bt_join;
	
	//누군가 이 객체를 생성할 때 호출하는 생성자 호출로 매개변수 값을 받아오자! 
	public MemberListener(JoinForm joinForm, Button bt_login, Button bt_join){ 
		this.joinForm=joinForm;
		this.bt_login=bt_login;
		this.bt_join=bt_join;
	}
		
	//@override
	public void actionPerformed(ActionEvent e){
		/*이벤트를 일으킨 컴포넌트가 Object형으로 반환 
		ActionEvent는 버튼만 일으킬 수 있는 것X, 
		클릭이 가능한 모든 컴포넌트가 Action이 적용될 수 있음 
		따라서 Button에 국한되지 않는 상위자료형으로 받기*/
		Object obj=e.getSource();
		//Button bt=(Button)e.getSource(); 이렇게도 가능
		
		if(obj==bt_login){
			System.out.println("로그인 버튼 누름");
			joinForm.checkForm();
		}else if(obj==bt_join){
			joinForm.checkForm();
			System.out.println("가입 버튼 누름");}
	}
}
