package instrument;

public class Drum extends MusicTool{
	public void setVolume(){ //컴파일이 성공되려면 부모의 불완전한 메서드(추상메서드) 재정의하기
		System.out.println("드럼의 소리를 높여요");
	}
}
