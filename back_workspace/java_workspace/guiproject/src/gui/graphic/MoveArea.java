package gui.graphic;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

public class MoveArea extends JPanel{
	private int x;
	private int y; //은닉화
	
	public void move(){
		 x++;
		 y++;
	}
	
	//JPanel의 paint()메서드를 오버라이딩
	public void paint(Graphics g){
		//채워진 원 그리기
		g.setColor(Color.RED);
		g.fillOval(x,y,45,45); //버튼 눌렀을 때 x,y를 증가시키자. MoveTest로 ㄱㄱ
		
	}
/*	
	//getter
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
	//setter
	public void setX(int x){
		this.x=x;
	}
	public void setY(int y){
		this.y=y;
	}
*/
}
