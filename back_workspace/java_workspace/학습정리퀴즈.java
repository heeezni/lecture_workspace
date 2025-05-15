class Bird {
	private String name="새";
	boolean fly;
	public void fly(){
		System.out.println("부모가 날아요");
	}
}
class Duck extends Bird {
	String name="난 오리";
	public Duck(){
		(가) → super() 호출 숨겨져 있음
	}
	public void fly(){ (라) → 오버라이딩
		System.out.println("자식이 날아요");
	}
}
class UseTest {
	public static void main(String[] args){
		Bird b=new Bird(); (나)
		Duck d=new Duck(); (다) → Duck인스턴스가 생성되면, 부모 Bird의 초기화만 이루어지고, 부모의 인스턴스는 만들어지지 않는다.
              b=d; (마) →  b가 갖는 주소값은 d(오리 인스턴스)의 주소값
              b.fly(); (바) → this=b인데, (마)에서 b=d가 되었으므로, 자식의 fly()
	}
}

제 4의 자료형인 객체 자료형도 형변환 가능?

✔ 자바에서 객체 자료형도 형변환 가능.
단, 상속 관계가 있는 경우에만 형변환이 인정!

형변환의 두 종류

| **종류** | **방향**  | **예시**                                     | **형변환 허용 여부** | **안정성**                                      |
| ------ | ------- | ------------------------------------------ | ------------- | -------------------------------------------- |
| 업캐스팅   | 자식 → 부모 | `Bird b = new Duck();`                     | 자동 형변환 (O)    | 안전                                           |
| 다운캐스팅  | 부모 → 자식 | `Duck d = (Duck) b;`<br>(단, b가 실제 Duck일 때) | 강제 형변환 (O)    | 조건부 안전<br>(잘못하면 런타임 오류) |

부모자료형(상위자료형)으로 자식자료형을 가리키는건 자연스러움

다운캐스팅은 실제 객체가 자식이어야만 성공
-------------------------------------------------
package animal;
class Bird {
	protected String name="새";
	boolean fly;
	public Bird(Boolean f) {
		fly = f;
	}
}

package animal;
class Duck extends Bird {
	String sound="quack";
	
	//public Duck(){
	//	super(); → super(true/false); 넣어줘야 에러안남 
	//}
}

package animal;
class UseTest {
	public static void main(String[] args){
	        Bird b=new Bird(); (가) → 논리값을 매개변수로 받아야함
	        b.sound=”JJack JJack”; (나) → 존재하지 않는 변수접근. 자식 변수임
	        Duck d=new Duck(); (다) → 에러  Duck의 생성자에 super(true/false); 넣어줘야 에러안남
	}
}


자식은 자신이 보유한 영역에서 부모로 부터 물려받은 데이터 영역에 접근하기 위해,(위 서랍장)
부모 자료형으로 upcasting 하여 접근할 수 있다.

자식이 부모의 메서드를 오버라이드 하였으나, 부모의 메서드를 호출하려면 부모 자료형으로 upcasting 하여 호출할 수 있다.
(x, super())

상속 관계는 개념적 계층 구조에 따라 부모 자식이 결정되는 것이므로, 용량과 상관 없다.
------------------------------------------------------------------------------
다형성
public class Bird {
         int age=3;
	public void fly() {
		System.out.println("새가 날아요");
	}	
}
public class Sparrow extends Bird{
         int age=5;
	public void fly() {
		System.out.println("참새가 날아요");
	}	
}
public class Duck extends Bird{	
	public void fly() {
		System.out.println("오리가 날아요");
	}	
}

public class UseBird {
	public static void main(String[] args) {
	      Bird bird = new Bird();
		  System.out.println(bird.age);//(마) 3
          bird.fly(); //(가) 새가 날아요
	      bird=new Sparrow();
          bird.fly(); //(나) this에 sparrow가 바인딩, 참새가 날아요
	      bird=new Duck();
          bird.fly(); //(다) 오리가 날아요
	      Bird b=new Bird();
	      Duck d=(Duck)b; //(라) 컴파일 시 에러x, 실행시 에러O
	}
}

Bird b=new Bird()
Duck d=(Duck)b; 
//컴파일은 가능(같은 자료형이니까), 실행할 때 오류(bird 인스턴스만 존재)

★ 다형성(Polymorphism): 종은 같으나, 각 객체가 서로 다른 특징을 가지고 동작하는 현상

-------------------------------------------------------------------------------------