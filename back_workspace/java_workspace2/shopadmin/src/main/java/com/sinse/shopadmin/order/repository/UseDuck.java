package com.sinse.shopadmin.order.repository;
//오리를 여러 개 올리고 싶어하는 공격자
public class UseDuck {

	public static void main(String[] args) {
		Duck d=Duck.getD();
		System.out.println(d);
		System.out.println(d);
		System.out.println(d);
		System.out.println(d);
		System.out.println(d); 
		System.out.println(d); 
		System.out.println(d); 		
	}
}




