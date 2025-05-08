class Dog{
	String name="또리";
	int age=3;
	
	public void run(){
		System.out.println(name+"가 달립니다");
	}
	public static void main(String[] args){
		Dog d=new Dog();
		d.run();
		
		int age=5;
		
		//System.out.println(name+"가 달립니다"); 
		//static 메서드 안에서는 인스턴스 멤버(변수/메서드)에 직접 접근할 수 없다.
		//객체를 통해 접근해야 한다: d.name, d.run() 처럼.

		System.out.println(d.name+"가 달립니다"); 

	}
}

