class Phone{
	int price=200;
	String color="white";

	public void setPrice(){
		price = 500;
	}

	public void setColor(){
		color="red";
	}
}

public class UsePhone{
	public static void main(String[] args){
		int price=5;
		Phone  ph=new Phone();
		price=3;
		ph.setPrice();
		System.out.println(price);
	}
}

/* 
int price = 5;
main 메서드 내에서 지역 변수 price가 선언되며, 초기값은 5입니다.

price = 3;
price는 지역 변수이므로 이 값을 3으로 변경합니다. 이때 멤버 변수 price와는 별개로, 지역 변수 price가 변경됩니다.

Phone ph = new Phone();
Phone 클래스의 객체 ph를 생성합니다.
ph 객체의 멤버 변수 price는 200으로 초기화되어 있습니다.

ph.setPrice();
setPrice() 메서드를 호출하여 ph 객체의 인스턴스 변수 price 값을 500으로 변경합니다.
이 변경은 객체의 인스턴스 변수에만 영향을 미칩니다. 즉, ph.price는 이제 500이지만, 지역 변수 price는 여전히 3입니다.

System.out.println(price);
price는 지역 변수로, main 메서드에서만 유효합니다.
ph.setPrice()가 호출되어도, 지역 변수 price는 영향을 받지 않으므로 여전히 3입니다.
따라서 출력은 "3"이 됩니다.
*/ 