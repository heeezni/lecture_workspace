class Member {
	int price; //(클래스가 보유한 멤버변수만) 초기값 할당하지 않아도 컴파일러에 의해 초기화 (기본값 0)
	int age=23; //인스턴스 변수
	static int money=23; //static 정적인 (딱 붙어있음)
	//new 연산자 써도, 인스턴스에 딸려올라가지 않고 원본에 딱 붙어있음
	//클래스 변수 (클래스에 붙어있음)
	
	public void talk(){
	}
}

 public class UseMember{//.java 파일에는 public 클래스는 하나만 가능. 파일명은 public 클래스명과 같아야 함
	 static String k="안녕"; 
	 public static void main(String[] args){
		//방법1 : k는 static 변수이므로 UseMember.k처럼 클래스명으로 직접 접근 가능
		UseMember.k="헬로";
		
		//방법2 : 메인과 UseMember가 같은 클래스 안에 있을 경우(static이면서)
		k="하이";
	}
}