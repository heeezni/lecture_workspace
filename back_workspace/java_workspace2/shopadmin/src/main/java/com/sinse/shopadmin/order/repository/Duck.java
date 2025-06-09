package com.sinse.shopadmin.order.repository;
//오리를 한 번만 올리게 방어하자!
public class Duck {
	String name="도널드";
	private static Duck d;
	
	//생성자를 막아버린다.
	private Duck() {
	}
	
	public static Duck getD() {
		if(d==null) {
			d=new Duck();
		}
		return d;
	}
}


