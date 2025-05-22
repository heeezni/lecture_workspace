package com.sinse.ioproject;

public class ExceptionTest {
	/* 예외도 에러다
	 * 코드로 처리 가능하면 예외, 그렇지 않으면(시스템 상의 문제로) 에러
	 * Java가 개발자에게 예외처리할 것을 강요하는 체크예외 vs 언체크예외
	 * 공통점: 둘 다 코드로 해결할 수 있는 에러
	 * 체크 예외: 중요한 것들만 강요
	 * 언체크 예외: 개발자에게 맡김. 비정상 종료되지 않으려면 개발자 적극 예외처리
	 * */

	public static void main(String[] args) {
		
		/*int[] arr = new int[3];
		
		try {
			arr[0] = 1;
			arr[1] = 3;
			arr[2] = 6;
			arr[3] = 9; //에러가 발생했지만 언체크 예외
		} catch (MyArrayException e) {
			System.out.println(e.getMessage());
		}
	}*/
		//우리가 만든 예외를 일부러 일으켜보자
		
		try {
			throw new MyArrayException("배열관련 에러 발생");			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}
		System.out.println("도달");
	}
}
