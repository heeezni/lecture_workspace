class UseDuck{
	public static void main(String[] args){
		Duck d=new Duck(); //인스턴스를 생성해야 클래스 내의 변수 사용가능
		//Duck 클래스 자체는 오리를 생성할 수 있는 틀에 불과
		//사용하려면 반드시 인스턴스를 생성한 후 해당 인스턴스를 접근해야함
		System.out.println(d.name);
	}
}