package gui.chat;
import java.awt.Frame;
import java.awt.Button;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class ChatA extends Frame implements ActionListener, KeyListener{ 
//							is a					is a
	TextArea area;
	Panel p_south;
	TextField t_input;
	Button bt_open;
	ChatB chatB=null;
	
	public ChatA(){
		area=new TextArea();
		p_south=new Panel();
		t_input=new TextField(30);
		bt_open=new Button("열기");
		
		area.setBackground(Color.YELLOW);
		
		add(area);
		add(p_south, BorderLayout.SOUTH);
		p_south.add(t_input);
		p_south.add(bt_open);
		
		//버튼과 리스너인 자와의 연결
		bt_open.addActionListener(this);
		//텍스트 필드와 리스너인 자와의 연결
		t_input.addKeyListener(this);
		
				
		setSize(300,400);
		setVisible(true);
	}
	
	//ActionListener 인터페이스의 메서드를 오버라이딩
	public void actionPerformed(ActionEvent e){
		//System.out.println("나 눌렀어?");
		chatB=new ChatB(this);
		// this란 인스턴스가 자기 자신을 가리키는 레퍼런스 변수
	}	
	
	//KeyListener의 메서드를 오버라이딩
	public void	keyTyped(KeyEvent e){}
	public void	keyPressed(KeyEvent e){} //keydown
	public void	keyReleased(KeyEvent e){ //keyup
		if(e.getKeyCode()==KeyEvent.VK_ENTER){ //엔터키는 10번이므로 하지만 직관적인 상수로 표현하자!
			System.out.println("메세지 전송");
			//chatB가 보유한 area의 텍스트값을 원하는 값으로 넣자
			//chatB.area.setText("친구야 안녕"); //setText는 누적X
			String message = t_input.getText();
			area.append(message+"\n");
			chatB.area.append(message+"\n"); // \n은 줄바꿈
			//나의 텍스트 필드 다시 지우기
			t_input.setText(""); //초기화
		}
	}
	
	public static void main(String[] args) {
		new ChatA();
	}
}

/*
ChatB가 ChatA를 보유하게 하기
과제:친구도 날 따라서 보내고
*/