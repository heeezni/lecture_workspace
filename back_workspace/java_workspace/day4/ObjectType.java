// 자료형 (문자,숫자,논리값 + 객체자료형 
//java.exe의 대상이 되려면 반드시 실행부인 main()메서드가 존재해야함
class ObjectType{
	public static void main(String[] args){
		int x=5;
		// Java에서는 자료형이 총 4개가 지원된다.
		// 기본자료형(문자,숫자,논리값) + 객체자료형도 자료형이다
		
		Dog d=new Dog();
		// 고정관념에서 벗어나자, Java에서는 개발자가 정의한 클래스를 새로운 자료형으로 인정
		// 따라서 이 코드에서 변수 d앞에 선언해야 하는 자료형은 내가 정의한 Dog 형이다
		
		//강아지를 짖게해보자
		d.bark();
	}
}