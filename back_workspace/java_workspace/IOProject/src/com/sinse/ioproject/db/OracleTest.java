package com.sinse.ioproject.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleTest {
	public static void main(String[] args) {
		//오라클에 접속하기 
		/*
		 * 1) Java언어가 데이터베이스를 핸들링하기 위해서는 DB를 제작한 벤더사에서 제공하는 구현체인
		 * 	드라이버를 먼저 메모리에 로드해야한다. 단, 주의! 일반 클래스처럼 new 할 수없고, method 영역에 개발자가 직접 올려야한다
		 * */
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");  //Jvm의 메서드 영역에 개발자가 직접 로드
			System.out.println("드라이버 로드 성공");
			
			//원격지의 오라클 접속!
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "java", "1234");
			if(con!=null) {
				System.out.println("접속 성공");
			}else {
				System.out.println("접속 실패");
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("드라이버 로드 실패");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
