//현재 클래스가 위치한 패키지를 선언
package animal;

//패키지화 시킨 클래스는 외부의 다른 클래스들이 사용해야하므로 
//public으로 접근을 풀어주자
public class Dog{
	public void bark(){
		System.out.println("멍멍");
	}
}

// javac -d C: \bin주소 Dog.java