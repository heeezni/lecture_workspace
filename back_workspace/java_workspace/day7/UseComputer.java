class UseComputer {
	public void setting(Computer c , int s){ //c는 같은 객체(com) 참조 → heap에 speed 직접 접근 가능
		c.speed=s; 
		s=50;
	}
	public static void main(String[] args) {
		int k=500;
		
		UseComputer uc = new UseComputer();
		Computer com = new Computer();

		com.speed=100;
		
		uc.setting(com , k);
		
		System.out.println(com.speed); //500
		System.out.println(k); //500
		
	}
}
//다시 JVM메모리구조 확인

 /*uc.setting(com, k) 호출 시:

c는 같은 객체(com) 참조 → heap에 speed 직접 접근 가능

s는 k 값(500)만 복사됨 → 이후 s = 50 해도 k는 변하지 않음 */