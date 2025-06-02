package collection;

import java.util.ArrayList;
import java.util.List;

/*Collection
 * Java에서 '객체'들을 모아서 처리할 때 유용한 방법을 지원해주는 api 집합을 가리켜 
 * Collection FrameWork라고 함 (=자료구조)
 * 
 * [사물이 모여진 모습 유형]
 * -순서 있는 모습 : List(JS의 배열과 유사), Queue(FIFO선입선출) / 중복허용 O
 * -순서 없는 모습 : Set (홈런볼), Map <key-value> (키세스초콜릿) / 중복 허용X
 * */
public class ListTest {
	/*자바의 컬렉션 프레임웍은 java.util 패키지에서 지원하며
	 * 그 중, 순서 있는 집합을 처리하는데 대표적인  List에 대해 알아본다
	 * List vs 배열
	 * 공통점 - 순서를 가지며 인덱스로 접근가능
	 * 차이점 - 배열은 생성 시, 반드시 크기를 명시, 기본 자료형도 담을 수 있다
	 * 			컬렉션의 대상은 오직 객체만을 대상으로 (JS 배열과 유사)
	 * 컬렉션 프레임웍은 최상위 인터페이스들의 메서드를 주로 사용하기 때문에
	 * 하위의 어떠한 구현 객체를 사용하더라도, 메서드 사용이 일관상이 있다.
	 * 담을 때는 거의 add, 길이는 거의 size()
	*/
	//ArrayList, Vector 중요
	
	public static void main(String[] args) {
		
		List<String> list = new ArrayList(); // 고무줄 배열(JS와 동일)
		//<>안에 자료형을 명시하면, 컴파일러가 다른 자료형을 거부한다
		//즉, 컴파일 타임에 자료형 체크 **제너릭(Generic) 타입**

		list.add("apple");
		list.add("banana");
		list.add("grape");
		list.add("orange");

		//고전적 반복문
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		//Java 5부터 개선된 for문 (improved for)
		for(Object obj:list){ //list 크기만큼 반목문 돈다 - 데이터 다룰 때 쓴다(느림)
			System.out.println(obj);
		}
	}
}
