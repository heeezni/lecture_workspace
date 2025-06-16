/*
패널에 그림을 그리기 위해... 즉 paint()메서드를 재정의하려고
JPanel은 컨테이너 형이므로 내부에 그림이 없다 → 재정의 하기 좋음
*/
package gui.graphic;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

public class MyCanvas extends JPanel{
	Image image; //필요하면 보유하기
	
	public MyCanvas(){
		setBackground(Color.PINK); //페인트 메서드 재정의해서 날아감
		setPreferredSize(new Dimension(600,450));
	}
	//1) 이미지를 넘겨받을 메서드를 선언하자
	//2) 프레임을 보유하면, 프레임이 보유한 배열로 쓸 수 있다
	
	//1)방식
	public void setImg(Image image){ //setter주입
		this.image=image;
	}
	
	public void paint(Graphics g){
		//이미지 그리기
		/*ImageObserver란? 이미지를 로드는 시스템에 비동기이기 때문에 
		이미지가 완료되지 않은 상황에서, 옵저버에 의해 이미지가 완전히 로드될 수 있도록 내부적인 처리가 지원.
		이때 옵저버 역할을 수행할 객체를 개발자가 선택할 수 있는데, JPanel은 ImageObserver 인터페이스를
		구현한 객체이므로 옵저버 역할이 가능*/ 
		g.drawImage(image,0,0,600,450,this);
	}
}
