package gui.graphic;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;

public class Gallery extends JFrame implements ActionListener{
	JPanel p_north;
	JButton bt_prev, bt_next;
	GTitle la_title;
	
	Toolkit kit; //시스템의 이미지를 개발자 대신 얻어다 주는 객체
	MyCanvas myCanvas;
	/*자바스크립트와는 달리, 대부분의 언어는 배열이 고정배열이므로 반드시 배열선언 시 그 크기를 명시*/
	//이미지 객체를 모아놓을 배열 준비
	Image[] imgArray=new Image[9];
	//String[] path=String[9];
	String[] path=new String[] {
		"animal1.jpg",
		"animal2.jpg",
		"animal3.jpg",
		"animal4.jpg",
		"animal5.jpg",
		"animal6.jpg",
		"animal7.jpg",
		"animal8.jpg",
		"animal9.jpg"
	};
	int index = 0;
	
	public Gallery(){
		p_north=new JPanel();
		bt_prev=new JButton("이전");
		la_title=new GTitle(path[index]);
		bt_next=new JButton("다음");
		
		kit=Toolkit.getDefaultToolkit(); //툴 킷의 인스턴스 얻기
		
		myCanvas=new MyCanvas();
		
		p_north.add(bt_prev);
		p_north.add(la_title);
		p_north.add(bt_next);
		p_north.setPreferredSize(new Dimension(600,50));
		add(p_north, BorderLayout.NORTH);
		add(myCanvas);
		
		//초기 이미지 보여주기
		createImg();
		
		//패널에 초기 이미지 한 개를 전달해주자
		myCanvas.setImg(imgArray[index]); //이미지 전달
		
		//버튼과 리스너 연결
		bt_prev.addActionListener(this);
		bt_next.addActionListener(this);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,500);
		setVisible(true);
	}
	
	//멤버변수로 선언된 이미지 배열에, 이미지 인스턴스 9개를 준비해 놓아야 프로그램 가동과 동시에 사용자가 사용할 수 있음
	public void createImg(){
		
		for(int i=0; i<path.length; i++){
			//툴킷으로부터 이미지 인스턴스 9개 생성하여 image 배열에 넣어주기
			imgArray[i]=kit.getImage("C:/lecture_workspace/back_workspace/java_workspace/guiproject/res/geographic/"+path[i]);
		}
	}
	
	// 패널에 이미지 출력하기
	/*그래픽 프로그래밍에서 컴포넌트의 약간의 변화라도 생기면 그 그림은 지워지고
	다시 그려져야 하는데, 개발자가 처리하는 게 아니라, 시스템이 알아서 렌더링을 담당
	컴포넌트의 변경이 생기면 다음의 과정을 거쳐서 그래픽이 구현된다
	[AWT]
	최초 컴포넌트 등장 시 paint()에 의해 눈에 보여짐
	사용자가 컴포넌트의 그림에 변화를 주게되면, JVM은 기존 그림을 지워야하므로
	update()메서드를 호출하여 그림을 깨끗하게 지움. + 내부적으로 paint()메서드를 호출하여 변경된 그림을 화면에 보여준다
	
	[Swing]
	사용자가 컴포넌트에 변화를 주게되면 update()메서드가 호출되는 것이 아니라
	paintComponent()를 호출하여 화면을 깨끗하게 지움 + 변경된 그림을 다시 보여주기위해 스스로 paint()호출
	*/
	public void showImg(int n){
		index+=n; //다음 사진일 경우 인덱스 증가
	
		if (index >= imgArray.length) {
			index = 0;
		} else if (index < 0) {
			index = imgArray.length - 1;
		}
		myCanvas.setImg(imgArray[index]);
		//변경된 이미지를 보기 위해서는 사용자의 윈도우 조작이 아니라
		//개발자가 프로그래밍적으로 지우고 다시 그릴 것을 요청해야함
		//이때 호출되는 메서드가 바로 **repaint()**다시 그려줄 것을 부탁하는 메서드
		//개발자는 절대로 paint()를 직접 호출하면 안된다 (그림은 시스템이 알아서 그리기 때문에)
		//repaint() → update(AWT) →→→→→→→→→→ paint()
		//			   → paintComponent(Swing) →→paint()
		myCanvas.repaint(); //패널을 다시 그려줘~
		la_title.setText(path[index]);
	}
	
	//리스너 메서드 오버라이딩
	public void actionPerformed(ActionEvent e){
		//이벤트를 일으킨 컴포넌트=이벤트 소스
		Object obj=e.getSource();
		//버튼 인스턴스의 주소값만 비교하면 되므로, 굳이 형변환 할 필요 x
		if(obj==bt_prev){
			showImg(-1);
		}else if(obj==bt_next){
			showImg(1);
		}
	}
	
	//공부목적상 프레임의 그림을 뺏어서 그려보자
	/*public void paint(Graphics g){
		System.out.println("나 그려짐");
	}*/
	
	public static void main(String[] args) {
		new Gallery();
	}
}