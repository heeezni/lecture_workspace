package instrument;
// 가이드라인인, 악기들의 최상위 클래스인 MusicTool을 상속 받자
import riding.Roller;

// 추상클래스를 상속받는 자식은, 반드시 부모의 불완전한 메서드인
// 추상메서드를 완전하게 구현할 의무를 가진다 (구현강제) - 안전장치!
public class Piano extends MusicTool implements Roller{ // implements "인터페이스 부착할거다"
						/* is a */  				/* is a (can do) */ 
	
	// 부모의 메서드를 자식이 재정의하는 메서드 정의기법
	// '오버라이딩' 의무가 생김
	public void setVolume(){
		System.out.println("피아노의 소리를 높일게요");
	}
	
	public void roll(){
		System.out.println("피아노를 타고 가요"); //인터페이스 메서드 구현 강제!
	}
}

