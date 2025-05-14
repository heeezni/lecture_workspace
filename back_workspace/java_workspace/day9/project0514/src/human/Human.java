//OOP에서는 클래스 정의시 중복되는 코드의 재사용성을 위해 
//'상속'이라는 클래스 정의법을 이용할 수 있다.
package human;

public class Human{
	//parent : GUI 프로그래밍에서 컨테이너 ↔ child
	//super : 상속관계에서의 부모객체 ↔ sub
	public String skinColor;
	public int leg=2;
	public String hair;
	public boolean includeKorean;
	
	public void intelliThink(){
		System.out.println("지적인 사고력");
	}
}