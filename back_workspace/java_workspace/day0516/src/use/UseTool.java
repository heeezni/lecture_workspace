package use;
import instrument.Flute;
import instrument.Trumpet;

import instrument.Piano;
import instrument.MusicTool;
import instrument.Drum;
import riding.Roller;

class UseTool{
	public static void main(String[] args){
		//Flute 볼륨테스트
		//Flute flute = new Flute();
		//flute.volumeUp();
		//Trumpet trumpet=new Trumpet();
		//trumpet.setVolume(52);
		
		/*오케스트라 협주.. 모든 악기 한꺼번에 볼륨조절하려면?
		문제1. 각 악기가 보유한 메서드가 무엇인지 알 수가 없다.
		메서드 명의 일관성 부재
		→ 메서드를 통일해야함
		기술적으로 개발자들에게 업무규칙을 만들어 제제를 가해야함
		(가이드라인 제시) 기준이 되는 클래스를 만들자!
		*/
		
		// new MusicTool(); → 에러. 추상클래스는 인스턴스화 될 수 없다
		
		//Piano p = new Piano();
		MusicTool p = new Piano(); //업캐스팅
		p.setVolume();
		
		MusicTool d=new Drum();
		d.setVolume();
		// 종류는 MusicTool하나지만 동작은 piano,drum 여러가지로 할 수 있으므로 '다형성'
		
		Roller p2 = new Piano();
		p2.roll();
		//인터페이스도 is a 관계로 인정받음, 따라서 형변환 가능
	}
}

