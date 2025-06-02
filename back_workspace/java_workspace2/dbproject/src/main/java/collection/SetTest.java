package collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class SetTest {
	
	public static void main(String[] args) {
		//컬렉션 프레임웍이 지원하는 순서 없는 객체 중 하나인 Set
		Set<String> set=new HashSet<>(); // java7부터 다이아몬드 연산자 적어놓으면 스스로 추정, 자료형 생략 가능
		
		set.add("BMW");
		set.add("Benz");
		set.add("Audi");
		set.add("K9");
		
		//순서없는 set은 크기는 알 수 있지만, 직접적으로  for문 수행 불가 ➡ 풀어 놓아야 함
		Iterator<String> it =  set.iterator(); //순서없는 애들은 iterator로 늘어놓고 세야함
		
		while(it.hasNext()) { //다음요소가 존재 할 때까지
			String obj=it.next(); //현재 위치에서 다음요소로 접근
			System.out.println(obj);
		} 
	}

}
