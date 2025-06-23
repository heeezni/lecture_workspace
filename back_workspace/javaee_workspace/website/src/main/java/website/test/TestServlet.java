package website.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html"); //텍스트기반의 문서이면서 html이다
		response.setCharacterEncoding("utf-8");
		
		//웹 브라우저에 출력될 문자열을 처리할 출력 스트림
		PrintWriter out = response.getWriter();
		out.print("인희진");
	}

}
