class Bullet {
    String bullet;

    public Bullet(String bullet) {
        this.bullet = bullet;
    }	
}


public class Hero {
	int hp=10;
	boolean fly=false;
	String name="수퍼마리오";
	Bullet bullet;
	
	public void setHp(int hp) { //멤버변수와 매개변수 이름 똑같이 가기(변수값 변경을 한다는 의도 강하게 어필)
		this.hp=hp;
	} //멤버변수 hp 값을 변경하고 싶다 
	
	public void setFly(boolean fly) {
		this.fly=fly;
	}//멤버변수 fly 값을 변경하고 싶다
	
	public void setName(String name) {
		this.name=name;
	}//멤버변수 name 값을 변경하고 싶다	
	
	public void fire(Bullet bullet){ //Bullet도 자료형
		this.bullet= bullet;
	}//멤버변수 bullet 을 다른 무기로 변경하고 싶다
	

	public static void main(String[] args) {
		Hero hero = new Hero();
		hero.setHp(20);
		hero.setFly(true);
		hero.setName("루이지");
		hero.fire(new Bullet("knife"));

	}	
	
}

