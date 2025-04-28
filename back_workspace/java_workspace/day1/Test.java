class Test{
	public static void main(String[] args){
		for(int i=2; i<10; i++){
			for(int n=1; n<10; n++){
				System.out.println(i+"*"+n+"="+(i*n));
			}
		}
	}
}	



/*
javascript는 개발자가 변수에 할당한 데이터의 종류판단을 실행할 때마다 해석
interpreter 언어 - 브라우저로 실행할 때마다.. 매번...
장점- 수시로 데이터를 바꾸면서 그 결과를 확인할 때

java는 자료형에 대한 판단을, 실행 전에 문법 검사 시에 시도
그 결과를 파일로 저장해놓고, 프로그램을 실행할 때는 두 번 다시 자료형이나 문법에 대한 판단x
compile 컴파일 언어 

javac Test.java (컴파일)
java Test (실행)
ls

.class -기계어
.java - 인간용

자바 클래스 실행하려면 반드시 메인 메소드 정의
이미 정해진 함수명이므로 절대 다르게 해서는 안됨
코드가 단 한 자라도 수정되면 다시 기계어 만들어야함 (재컴파일)
*/