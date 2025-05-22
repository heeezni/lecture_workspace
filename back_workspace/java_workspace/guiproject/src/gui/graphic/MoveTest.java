package gui.graphic;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class  MoveTest extends JFrame implements ActionListener{
	JPanel p_north;
	JButton bt;
	MoveArea p_center;
	
	public MoveTest(){
		p_north=new JPanel();
		bt=new JButton("이동");
		p_center=new MoveArea();
		
		p_north.setPreferredSize(new Dimension(600,50));
		p_north.add(bt);
		add(p_north, BorderLayout.NORTH);
		add(p_center);
		
		bt.addActionListener(this); //버튼과 리스너 연결
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,650);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		//MoveArea의 빨간색 원을 이동시키자
		p_center.move();
		//다시 그려줘
		p_center.repaint(); //절대 paint()호출은 안됨!
		
/*		//x,y를 증가시키자
		int x=p_center.getX();
		x++;
		p_center.setX(x); //얻어와서, x증가시킨 후, 주입
		
		int y=p_center.getY();
		y++;
		p_center.setY(y);
*/
	}
	
	public static void main(String[] args){
		new MoveTest();
	}
		
}
