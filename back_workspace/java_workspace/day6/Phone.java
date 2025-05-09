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
