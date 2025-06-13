package com.sinse.shopadmin.product.view;

public class ExceptionTest3 {

	public static void main(String[] args) throws NumberConvertFailException{ //JVM에 에러처리 전가 -> B만나지 않음
		System.out.println("A");
		
		throw new NumberConvertFailException("내가 만든 예외");
		
		//System.out.println("B"); //에러 안잡을 시 여기만 빨간줄, 왜? 에러를 일으켜서 실행부가 닿지 않는다
	}
}

/*에러 잡는다 1) try-catch
				2)책임 전가 (throws)

*왜 에러를 만들까?
*MemberNotFoundException (예외 커스텀)
*
*/