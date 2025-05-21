package gui.chat;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class ChatB extends Frame implements KeyListener{
	TextArea area;
	Panel p_south;
	TextField t_input;
	ChatA chatA=null; //ChatB가 ChatA를 제어하기 위해 has a 관계로 보유함
	
	public ChatB(ChatA chatA){ //생성자 호출시 주소값을 넘겨야 하므로, 
											//이 생성자 메서드 호출하는 자 call by reference로 호출하는 것
		//System.out.println("저 태어날 때 ChatA정보 넘겨받았어요"+chatA);
		area=new TextArea();
		p_south=new Panel();
		t_input=new TextField(30);
		this.chatA=chatA; //새롭게 인스턴스를 생성하지 말고, 기존의 ChatA를 넘겨받음
		
		t_input.addKeyListener(this);
		
		add(area);
		add(p_south, BorderLayout.SOUTH);
		p_south.add(t_input);
		
		area.setBackground(Color.PINK);
		
		setSize(300,400);
		setVisible(true);
	}
	//KeyListener의 메서드를 오버라이딩
	public void	keyTyped(KeyEvent e){}
	public void	keyPressed(KeyEvent e){} //keydown
	public void	keyReleased(KeyEvent e){ //keyup
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			//String message1 = chatA.t_input.getText(); // chatB에서 채팅할 땐 chatA 입력 필드에 아무것도 없음
			//area.append(message1+"\n"); //그니까 필요없음
			
			//내가 입력한 값
			String message = t_input.getText();
			area.append(message+"\n");
			chatA.area.append(message+"\n"); // \n은 줄바꿈
			t_input.setText("");
		}
	}
}
