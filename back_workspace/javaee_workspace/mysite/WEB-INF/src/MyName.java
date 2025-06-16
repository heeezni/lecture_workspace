package com.sinse.mysite.test;

/*Java의 클래스를 이용하여, 나의 이름을 웹 브라우저에 출력해보자
자바의 클래스 중 오직 java EE기반의 서버에서만 해석 및 실행될 수 있는 클래스를 가리켜
**서블릿(Servlet)**이라 한다*/
import jakarta.servlet.http.HttpServlet;
import java.io.IOException; //standard 패키지
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyName extends HttpServlet{
							//서블릿으로 정의하려면 서블릿을 상속받아야 한다.
// 서블릿은 개발자가 임의로 메서드를 정의하는 것이 아니라, 이미 정해진 메서드가 있음	

	// 부모인 서블릿의 doGet을 오버라이드 하자
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	// 웹 브라우저에 보여질 html문서를 작성해본다
		PrintWriter out=response.getWriter(); //응답 정보를 출력할 스트림 얻기!
		out.print("This is my first app!");
		
	
	}
							
}