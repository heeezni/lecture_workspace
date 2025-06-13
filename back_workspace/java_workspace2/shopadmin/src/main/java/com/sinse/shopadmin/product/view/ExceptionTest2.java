package com.sinse.shopadmin.product.view;

public class ExceptionTest2 {
	
	public void test() {
		try {
			test2(); //RuntimeException이라 강제X
		} catch (NumberConvertFailException e) {
			System.out.println("내가 만든 예외가 전달되었네..."+e.getMessage()); //출력 : 내가 만든 예외가 전달되었네... 숫자로 못바꿨어
			e.printStackTrace();
		}
	}
	
	public void test2() throws NumberConvertFailException{
		String s="100k";
		
		try {
			Integer.parseInt(s); //개발자가 처리할 수 있는 예외의 예시
		} catch (NumberFormatException e) {
			// 우리만의 예외를 메모리에 생성하고, 예외를 일부러 일으킨다
			throw new NumberConvertFailException("숫자로 못 바꿨어", e); // throw: 예외 발생시킴
			// 예외 잡지말고 외부에 전달하자! (try-catch or throws)
		}

	}
	
	public static void main(String[] args) {
		new ExceptionTest2().test();
	}

}
