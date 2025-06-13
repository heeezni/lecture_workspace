package com.sinse.shopadmin.product.view;
/*
 * 에러 vs 예외
 * 에러 : 개발자의 범위 벗어난 문제 상황 - 프로그램의 관심대상이 아님
 * 예외 : 자바에서 말하는 에러(개발자가 처리가능한 에러를 말한다 = 예외)
 * 									에러 중 개발자가 처리가능한 수준의 에러를 특히! 예외라고 함
 * 
 * 예외 中
 * 1) 컴파일러가 처리를 강요하는 예외 -  '강요된 예외' (컴파일타임에 강제)
 * 2) 강제하지 않는 예외 - 개발자가 원하면 처리할 수 있는 예외 (이 예외의 최상위 객체 : RuntimeException) (실행타임에 예외발생)
 * 							심각하지 않은 예외, 주로 개발자의 논리적 실수
 * */
public class ExceptionTest { 
	
	
	public void test() throws ClassNotFoundException{
		load();
	}
	
	
	// throws의 의미 : "나를 호출한 메서드에 **책임을 전가**시키겠다!"
	public void load() throws ClassNotFoundException{ 
			Class.forName("babo");

	}
	
	public static void main(String[] args) throws ClassNotFoundException { // 최종적으로 JVM에 맡겨버린다
		ExceptionTest et=new ExceptionTest();
		et.test();
	}
}
