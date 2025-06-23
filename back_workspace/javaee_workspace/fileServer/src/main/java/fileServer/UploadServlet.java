package fileServer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

public class UploadServlet extends HttpServlet {
	// 클라이언트의 요청이 Post 방식이므로, doXXX형 중 doPost() 재정의
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8"); 
		
		PrintWriter out=response.getWriter();
		out.print("post 요청 받음");
		
		int maxLimit=3*1024*1024;  //3MB
		// 파일업로드 컴포넌트 중 cos.jar 써보기 
		MultipartRequest multi=new MultipartRequest(
				request, 
				"C:\\SSG I&C\\lecture_workspace\\back_workspace\\javaee_workspace\\fileServer\\src\\main\\webapp\\data",
				maxLimit,
				"utf-8"
				);
		// 한글 파일명 안 깨지게 하는 MultipartRequest :request객체, 경로, 용량, 인코딩
	}

}
