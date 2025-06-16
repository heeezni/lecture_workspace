/* 자동차 클래스로부터 인스턴스 1개 생성하여
자동차 핸들의 색상, 바퀴의 브랜드명, 문짝의 열기기능을 호출
*/
package use;
public class UseCar{
		
	public static void main(String[] args) {
		Car car=new Car(); //본체만 생성, has a관계인 부품은 아직 X
		
		/*car.handle=new Handle();
		car.wheel=new Wheel();
		car.door=new Door();
		*/
		
		System.out.println(car.handle.color);
		System.out.println(car.wheel.brand);
		car.door.open();
	}
}
