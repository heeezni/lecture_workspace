 /*
 JVM도 프로그램이므로, OS로부터 메모리를 할당받으면 
 해당 메모리를 3개의 영역으로 나누어서 관리한다
 1) Method: 클래스 바이트 코드가 로드되는 영역
				로드 시점은 실행할때(java.exe)이다.
 2) Stack: 모든 지역변수
			- 메서드 내에 선언된 지역변수
			- 메서드의 매개변수
 3) heap : 객체의 인스턴스가 올라오는 영역
 */
 class Cat{
	 String name="tomcat";
	 int age=5;
	 
	 public int getAge(){
		 return age;
	 }
	 
	 public static void main(String[] args){
		 
		 //java.exe 호출 시 main()메서드는 자동 호출 되는데,
		 //이때 개발자는 main()메서드의 매개변수인 String 배열의 값을 넘길 수 있다
		 //cmd> java Cat 문자열1 문자열2
		 //dir /s Cat.java 로 디렉터리 찾아서 cd 디렉터리 주소 
		 //(안나오면 한 단계 위 폴더에서 찾기 cd.. + dir) 
		 System.out.println("당신이 넘긴 배열의 수는"+args.length);
		 for(int i=0; i<args.length;i++){
		 			 System.out.println(args[i]);
		 }

		  
		 int x=7;
		 Cat c=new Cat();
		 System.out.println(c.getAge()); // JVM에 의해 메서드 호출 시 자동으로 this 생성, this=c 대입
		 
	 }
 }
 
 /*
 
 Stack 영역
 [getAge()frame] > }만나면 없어짐
 this=c (동적바인딩)
 
 [main()frame]
 c @ Cat instance(name, age)
 x
 args=null 
 
 */