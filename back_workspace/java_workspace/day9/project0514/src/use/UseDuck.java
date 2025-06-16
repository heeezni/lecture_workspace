package use;

import animal.Bird;
import animal.Duck;

class UseDuck {
	public static void main(String[] args) {
		Bird b = new Duck(); //가능
		//자료형이 Bird이기 때문에 2층 서랍장(부모서랍장)
		b.name= 8;
		b.fly(); //fly() 프레임 내에서 this=b, 여기서 b는 Duck의 주소값 가리킴
		
		Duck d= new Duck();
		d.name=9; //아래층 서랍장
		d.eat(); //eat()프레임 내에서 this=d, eat()는 오리의 메서드로 인정 (물려받았다)
		
		Duck duck = new Duck();
		Bird bird=new Bird();
		bird=duck; //개념 상 큰 범위(bird)에서 작은 범위(duck)에 넣을 수 있음
		duck=(Duck)bird; //강제 형변환 상위 자료형을 하위 자료형에 넣을 때
	}
}
