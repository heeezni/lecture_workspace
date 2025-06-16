package gui.layout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Button;
import java.awt.TextField;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;

//is a와 has a 관계로 보기

//JoinForm은 extends를 선언하는 순간부터 is a 관계에 의해서 Frame이 되어버림
//따라서 JoinForm을 대상으로 new 하면 윈도우가 생성
public class JoinForm extends Frame{ 
	//필요한 재료들을 has a 관계로 보유하자 (멤버변수로 선언)
	Label la_title;
	Panel p_center;
	Label la_id;
	TextField t_id;
	Label la_pwd;
	TextField t_pwd;
	Label la_name;
	TextField t_name;
	Panel p_south;
	Button bt_login;
	Button bt_join;
	
	public JoinForm(){//생성자 정의
		la_title=new Label("회원가입");
		p_center=new Panel();
		la_id=new Label("ID");
		t_id=new TextField();
		la_pwd=new Label("Password");
		t_pwd=new TextField();
		la_name=new Label("Name");
		t_name=new TextField();
		p_south=new Panel();
		bt_login=new Button("Login");
		bt_join=new Button("Join");
		
		//스타일 적용
		la_title.setBackground(Color.PINK); //PINK는 상수
		t_id.setBackground(Color.PINK);
		t_pwd.setBackground(Color.PINK);
		t_name.setBackground(Color.PINK);
		
		//조립
		//북쪽 영역
		add(la_title, BorderLayout.NORTH);
		
		//센터 영역
		Dimension d=new Dimension(110, 25);
		la_id.setPreferredSize(d);
		t_id.setPreferredSize(d);
		la_pwd.setPreferredSize(d);
		t_pwd.setPreferredSize(d);
		la_name.setPreferredSize(d);
		t_name.setPreferredSize(d);
		
		//센터영역의 패널에 컴포넌트들 부착
		p_center.add(la_id);
		p_center.add(t_id);
		p_center.add(la_pwd);
		p_center.add(t_pwd);
		p_center.add(la_name);
		p_center.add(t_name);
		
		//패널을 센터영역에 부착
		add(p_center);
		
		//남쪽 영역
		p_south.add(bt_login);
		p_south.add(bt_join);
		add(p_south, BorderLayout.SOUTH);
		
		MemberListener memberListener=new MemberListener(this, bt_login, bt_join);
		
		bt_login.addActionListener(memberListener); //로그인 버튼과 리스너 연결
		bt_join.addActionListener(memberListener); //가입 버튼과 리스너 연결
		
		
		this.setSize(300,200); //this 써도 되고 안써도 되고
		setVisible(true);
	}
	
	//회원가입과 관련된 컴포넌트가 전부 회원가입폼 클래스에 존재하므로
	//폼에 대한 유효성 체크 또한 회원가입폼 클래스에서 진행하는 게 더 효율적
	public void checkForm(){
		//아이디를 입력하지 않은 경우. 경고!
		if(t_id.getText().length() <1){ //String 반환, (참고로 String은 객체임)
			System.out.println("아이디를 입력하세요");
		}else if(t_pwd.getText().length() <1){
			System.out.println("비밀번호를 입력하세요");
		}else if(t_name.getText().length() <1){
			System.out.println("이름을 입력하세요");
		}
	}
	
	public static void main(String[] args) {
		new JoinForm();
	}
}
