package imgCloud;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

// 클라이언트가 전송한 텍스트 데이터, 바이너리 데이터까지 처리해보기
public class UploadServlet extends HttpServlet {

	String savePath = "C:/SSG I&C/lecture_workspace/back_workspace/javaee_workspace/imgCloud/src/main/webapp/public";
	int maxLimit = 3 * 1024 * 1024; // 3MB

	/*
	 * 클라이언트가 상당히 많은 양의 바이너리로 요청을 시도, Post로 전송을 하기 때문에 doXXX형 메서드 중 doPost로 처리
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8"); // 전송되어지는 파라미터의 값들이 깨지지 않도록 인코딩 지정
		response.setContentType("text/html; charset=utf-8"); //JSP에서 Page 지시 영역과 동일!
																					// out 객체 얻기 전에 처리하자
		PrintWriter out = response.getWriter();
		// 업로드 처리
		try {
			MultipartRequest multi = new MultipartRequest(request, savePath, maxLimit, "utf-8");
			System.out.println("업로드 성공");

			// 텍스트 파라미터 추출
			String title = multi.getParameter("title");
			// 파일 업로드 컴포넌트를 이용하면, 파라미터 추출마저도 업로드 컴포넌트를 이용해야함!
			System.out.println("전송된 제목은" + title);
			out.print("전송된 제목은 " + title);

			// [파일 처리 관련]
			// 1) 이미 서버에 저장되어버린 이미지를 개발자가 원하는 이미지로 교체
			// 2) 클라이언트 측에서 파일명을 결정짓고 업로드하기 (2중 택1)
			
			/*
			 * Collection FrameWork
			 * - 객체를 모아서 처리할 때, 효율적으로 처리할 수 있도록 지원되는 java util 패키지에서 지원하는 api
			 * 
			 * 1) 순서 있는 모음
			 * 		배열은 X, 기본자료형도 제어, 즉 객체만을 다루진 않음
			 * 		List
			 * 
			 * 2) 순서 없는 모음
			 * 		Set
			 * 		Map (key-value)
			 * 순서없는 컬렉션 객체를 처리할 떄 사용되는 도구가 2가지 유형이 있다 (Enumeration, Iterator)
			 * 그 중 옛날 방식 Enumeration 제어해본다
			 * */
			Enumeration<String> en=multi.getFileNames();
			while (en.hasMoreElements()) {
				String param = (String) en.nextElement();
				//out.print(" 업로드 된 파라미터명은 "+param);
			
				// 파라미터명을 이용하면, 업로드된 파일명도 추출가능
				String filename=multi.getOriginalFileName(param);
				out.print(" 업로드된 파일명은 "+filename);
			
			}

		} catch (IOException e) {
			System.out.println("업로드 실패ㅠㅠ");
			e.printStackTrace();
		}
	}

}
