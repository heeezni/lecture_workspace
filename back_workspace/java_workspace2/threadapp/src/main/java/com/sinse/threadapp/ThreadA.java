package com.sinse.threadapp;

//개발자는 하나의 자바 프로그램 내에서 비동기적으로 동시에 실행하고 싶은 코드가 있다면
//Thread로 정의하면 된다 
//이때 코드는 Thread의 run()메서드에 작성해야 한다
//주의 : 이 run()메서드는 개발자가 절대로 직접호출해서는 안된다.
//run()은 OS가 해당 스레드를 스케줄러에 의해 선택한 순간, JVM이 호출하는 것이기 때문에
//만일 개발자가 run()을 호출하면 그냥 일반메서드 호출이 되며, OS와 JVM이 관여하지 않는다
public class ThreadA extends Thread{ //import X, java.lang에서 지원하는 기본 클래스
	
	public void run() {
    	for(int i=1; i<=50; i++) {
    		System.out.println("A");
    	}
	}
}
