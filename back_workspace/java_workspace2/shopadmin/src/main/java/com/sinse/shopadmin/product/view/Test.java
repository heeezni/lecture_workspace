package com.sinse.shopadmin.product.view;

public class Test {

	public static void main(String[] args) {
		
/*		for(int i=0; i<5; i++) {
			System.out.println(System.currentTimeMillis()); //현재 시간 ms 반환
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
*/
/*		String path="C://test/document/mario.test.jpg";
		
		//1)가장 마지막 점의 위치를 알아낸다 lastIndexOf()
		int index = path.lastIndexOf(".");
		System.out.println(index);
		//2)가장 마지막 점의 위치 바로 다음 위치부터~ 가장 마지막 문자열까지 추출한다
		//	전체 문자열에서 일부 문자열 substring()
		String ext= path.substring(index+1, path.length());
		System.out.println(ext);
*/
		String v="a100";
		String v2="100";
		Integer.parseInt(v2); //에러 없음
		
		try {
			Integer.parseInt(v); //에러남 -> 직접 예외처리
		} catch (NumberFormatException e) {
			System.out.println("올바른 정수값을 입력해주세요.");
			e.printStackTrace();
		} 
		
	}
}
