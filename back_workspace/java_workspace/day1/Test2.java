/*
변수와 자료형
*/
class Test2{
	// 자바의 실행부. 이 함수가 없다면 java.exe의 대상이 될 수 없다
	// =실행될 수 없다
	public static void main(String[] args){
		String str = "대한민국";
		
		
		/* java도 기존 언어의 전통을 이어받았기 때문에
		기본 자료형은 문자, 숫자, 논리값 3가지
		
		자바의 문자는 문자와 문자열로 구분
		자바의 문자 자료형 char
		char a = '우'; //한 글자를 '문자형'이라고 함 character형
		String str = "대한민국"; //두 글자 이상의 문자집합을 '문자열'이라고 함
		
		자바의 논리값은 js 및 다른 언어와 동일 T/F
		boolean b = true;
		※주의: 다른 언어에서는 1이 true, 0이 false를 대신할 수 있지만
		자바는 철저히 T/F로 표기해야함
		
		자바의 숫자형은 크게 정수와 소수점을 지원하는 실수로 구분
		정수는 용량에 따라 byte < short < int < long
		실수는 float < double
		int x = 75;
		y = 5.6; //자바에서는 개발자가 소수점만 적으면 무조건  double로 생각
		float 쓰고싶으면 float z=7.2f
		*/
		System.out.println(str);
	}	
	
}
