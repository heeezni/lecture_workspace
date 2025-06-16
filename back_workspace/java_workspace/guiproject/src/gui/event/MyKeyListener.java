package gui.event;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/*OS를 거쳐 JVM으로부터 전달되는 키보드 이벤트를 청취하기 위한 객체인
KeyListener를 재정의 해보자*/
public class MyKeyListener implements KeyListener{
									  // is a
	//KeyListener가 보유한 추상메서드를 재정의
	
	//keyReleased와 비슷해서 정의만
	public void keyTyped(KeyEvent e){
	}
	
	//키보드 누를 때 (Js-keydown)
	public void keyPressed(KeyEvent e){
		System.out.println("눌렀어?");
	}
	
	//키보드 눌렀다 뗄 때 (Js-keyup)
	public void keyReleased(KeyEvent e){
		System.out.println("눌렀다가 뗐어?");
	}
}
