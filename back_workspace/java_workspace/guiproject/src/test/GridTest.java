package test;
import java.awt.*;
// GridLayout : 격자. 행과 열을 지원하는 배치 방법
class GridTest {
	public static void main(String[] args) {
		Frame f=new Frame("그리드 배치");
		f.setSize(500,500);
		
		
		f.setLayout(new GridLayout(3,4)); //3층의 4호수
		for(int i=1; i<=3; i++){
			for(int j=1; j<=4; j++){
				Button bt=new Button(i+"0"+j+"호");
				//Color 클래스가 보유한 PINK 상수 (static)
				bt.setBackground(Color.PINK);
				f.add(bt);
			}
		}
		
		f.setVisible(true); //가장 마지막에 호출하기
		/*레이아웃 설정과 컴포넌트 추가를 모두 끝내고 
		setVisible(true)를 맨 마지막에 호출해야 정상적으로 보입니다:*/
	}
}
