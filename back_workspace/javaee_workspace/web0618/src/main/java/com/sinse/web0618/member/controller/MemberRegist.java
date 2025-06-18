package com.sinse.web0618.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sinse.web0618.exception.MemberException;
import com.sinse.web0618.member.model.Member;
import com.sinse.web0618.member.repository.MemberDAO;

// 클라이언트의 가입 요청을 처리하는 회원 관련 서블릿
public class MemberRegist extends HttpServlet {
	MemberDAO memberDAO = new MemberDAO();

	// 웹브라우저인 클라이언트가 POST로 전송을 하면 서버는 반드시 doPost()메서드로 요청을 처리해야함
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 네트워크를 타고 전송된 파라미터와 그 값들을 받아서 처리하자
		// 이 때 사용되는 객체가 바로 '요청' 정보를 가진 request 객체이다.
		// 추출해서 변수에 담기
		String id = request.getParameter("id"); // html 컴포넌트에 부여한 name 명시
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");

		System.out.println("전송된 아이디는 " + id); // Tomcat서버의 콘솔에 출력됨
		System.out.println("전송된 패스워드는 " + pwd); // Tomcat서버의 콘솔에 출력됨
		System.out.println("전송된 이름은 " + name); // Tomcat서버의 콘솔에 출력됨
		
		// DB insert
		Member member=new Member();
		member.setId(id);
		member.setPwd(pwd);
		member.setEmail(name);
		String resultMsg=null;
		
		try {
			memberDAO.insert(member);
			resultMsg="회원가입 성공";
		} catch (MemberException e) {
			resultMsg="회원가입 실패";
			e.printStackTrace();
		}
		

		// 회원가입 후에는, 유저가 보게될 디자인 코드를 작성하여 결과를 보여줘야한다.
		/* 이 메서드의 매개변수 2개 중 
		첫번 째 매개변수인 request 객체는, 클라이언트 요청정보를 가지고 있고
		두번 째 매개변수인 response 객체는, 클라이언트가 보게 될 응답정보를 개발자가 구성할 수 있는 객체 */
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter(); //문자 기반의 출력 스트림
		out.print("<!DOCTYPE html>");
		out.print("<html lang=\"en\">");
		out.print("<head>");
		out.print("<meta charset=\"UTF-8\">");
		out.print("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		out.print("<title>Document</title>");
		out.print("</head>");
		out.print("<body>");
		out.print("회원가입을 축하드려요❤❤");
		out.print(resultMsg);
		out.print("</body>");
		out.print("</html>");

		
	}
}
