package gui.layout;
 /*
 GUI 프로그래밍을 윈도우 프로그래밍이라고도 함
 모든 UI 컴포넌트는 윈도우 안에서 구현되기 때문
 
 컴포넌트 : 재사용 가능한 객체 단위 (ex. gui 분야에서는 버튼, 체크박스 등...)
 [Java 컴포넌트의 유형]
 - 남을 포함할 수 있는 유형 (컨테이너형)
 (ex. Frame)
 특징: 남을 포함하려다 보니, 어떻게 배치할 지를 고민함
	   따라서 컨테이너형에는 배치관리자 (Layout Manager)가 지원됨
	   컨테이너 유형은 개발자가 배치관리자를 지정하지 않아도, 시스템에서 기본으로
	   적용되는 배치관리자가 있음..예) Frame의 경우는 BorderLayout이 적용
	   
	   <배치관리자 종류>
			1) BorderLayout (동, 서, 남, 북, 센터의 방향을 갖는 배치)
				각 방향에 들어가는 컴포넌트는 자신의 크기를 잃어버리고, 확장해버림
				그래서 대왕버튼이 만들어졌음
				
			2) FlowLayout (위치가 결정되지 않고 컨테이너 크기에 따라 흘러다님)
				단, 방향성이 있어서 수평방향의 흐름/수직방향의 흐름 둘 중 하나임
				
			3) GreedLayout (행과 열의 배치방식)
				각 행, 열에 배치되는 즉 셀에 들어가는 컴포넌트가 자신의 크기를 잃어버리고 확장해버림
				
			4) CardLayout (마치 포커처럼 오직 하나의 카드만 보여지는 배치방식)
				화면 전환 시 사용되는데, 사실 직접 만들어 구현해도 되기 때문에 몰라도 됨 ㅋ
 
 -남에게 포함당할 수 있는 유형 (비주얼 컴포넌트형)
 (ex. 버튼, 체크박스, 초이스 .. 윈도우 안에 포함되는 것들)
 */
import java.awt.Frame;
import java.awt.Button;
import java.awt.BorderLayout;
import java.awt.Panel; //눈에 보이지 않음 (div와 완전 흡사)
import java.awt.Color;

public class LayoutTest {
	public static void main(String[] args){
		//윈도우 생성
		Frame frame=new Frame("배치 학습");
		
		Panel panel; //윈도우 안에 소속되는 컨테이너형 컴포넌트
		//따라서 다른 컴포넌트를 포함할 수 있다
		//Panel도 컨테이너 형이므로 배치관리자가 지원되며, 개발자가 지정되지 않으면 디폴트가 'FlowLayout'
		panel=new Panel();
		Panel panel2=new Panel();
		
		//버튼 하나를 생성하여 부착해보자 (방향 지정하지 않으면 디폴트=센터)
		Button bt_center1=new Button("CENTER1");
		Button bt_center2=new Button("CENTER2");
		Button bt_west=new Button("WEST");
		Button bt_south=new Button("SOUTH");
		
		panel.add(bt_south); //패널에 버튼 부착! (버튼이 생성된 후 부착하기)
		panel2.add(bt_center1);
		panel2.add(bt_center2);
		
		frame.add(panel2);
		frame.add(bt_west, BorderLayout.WEST); //두번째 매개변수로 상수를 지정
		//상수는 public static final로 선언되었고, 클래스 소속이므로 인스턴스 생성 없이 사용가능
		//따라서 BorderLayout이 보유한 상수명으로 접근 가능
		frame.add(panel, BorderLayout.SOUTH);
		
		bt_center1.setBackground(Color.PINK);
		bt_center2.setBackground(Color.ORANGE);
		bt_west.setBackground(Color.YELLOW);
		bt_south.setBackground(Color.YELLOW);
		
		
		
		frame.setSize(500,400);
		//윈도우는 보이고, 안보이게 하는 기능이 있기 때문에 디폴트는 눈에 안보임
		frame.setVisible(true);
	}
}
