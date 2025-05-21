package gui.swing;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ConFig extends JFrame implements ActionListener{
	JTextField t_size;
	JButton bt;
	//has a관계는 멤버변수로 보유한 관계
	MyWin myWin=null;
	
	public ConFig(MyWin myWin){
		//외부에서 MyWin을 전달받는다
		//이 생성자 함수를 호출하는 자는 주소값에 의한 생성자 호출 시도해야함 (콜바이레퍼런스)
		this.myWin=myWin; 
		
		t_size=new JTextField(10);
		bt=new JButton("설정 적용");
		
		//컴포넌트들이 자신 본연의 크기를 유지하기 위해
		setLayout(new FlowLayout());
		
		add(t_size);
		add(bt);
		
		bt.addActionListener(this);
		
		setBounds(500+300,100,200,150);
		setVisible(true);
	}
	
	//리스너 메서드 오버라이딩
	public void actionPerformed(ActionEvent e){
		//MyWin이 보유한 area의 폰트 크기를 설정해주자
		//폰트 크기 값은 나의 TextField로부터 얻은 값이다
		int size=Integer.parseInt(t_size.getText()); //"55" //임포트 안해도 됨 java.lang에 들어있다
		//parseInt()는 static 메서드 → 객체 생성 없이 클래스명으로 바로 호출 가능
		myWin.area.setFont(new Font(null,0,size));
		
		/*
		Java Wrapper클래스가 있음
		
		자바의 기본 자료형은 3가지 (문자,숫자,논리값)
		기본자료형과 객체자료형간 변환이 가능하도록 지원되는 객체들이 있는데,
		이런 객체들을 Wrapper 클래스라고 함
		Integer(int x=3); => 객체 3
		객체.메서드(), 객체.속성 가능
		지원되는 이유? 기본자료형으로 할 수 없었던 더욱 많은 일을 하기 위해
		Integer.parseInt("45") =>객체 45
		
		Wrapper 클래스
		int-Integer 
		char-Character
		나머지는 기본 자료형에 대문자
		*/
	}
}
