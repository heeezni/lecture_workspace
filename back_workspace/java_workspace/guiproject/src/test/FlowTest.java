package test;
/*
FlowLayout
*/
import java.awt.*;
class FlowTest{
	public static void main(String[] args) {
		Frame f=new Frame("플로우 배치방식");
		f.setSize(200,250);
		
		//아무것도 명시하지 않으면 디폴트 배치 관리자가 적용되는데
		//Frame의 경우 디폴트는 BorderLayout
		//BorderLayout안에 배치된 컴포넌트는 Frame의 경계선까지 확장
		//하지만 FlowLayout은 컴포넌트 본연의 크기를 유지해줌
		f.setLayout(new FlowLayout());
		
		for (int i=0;i<20;i++){
			f.add(new Button("버튼"+i));
		}
		
		f.setVisible(true);
	}
}
