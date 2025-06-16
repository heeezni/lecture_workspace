class Bird{
	String name="독수리";
	
	public String getName(){
		return name;
	}
	
	public static void main(String[] args){
		int x=5; //기본 자료형이므로 x변수가 직접 담을 수 있다
		Bird b=new Bird();
		b.getName();
		
		Bird b2=new Bird();
		b2.getName();
		
		// System.out.println(b); //Bird@279f2327 : (새의 주소값) JVM가 알고있는 가상주소
	}
}

