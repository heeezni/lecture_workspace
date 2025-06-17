/*아래의 클래스는 오직 웹 애플리케이션 서버에서만 해석 및 실행될 수 있는 JavaEE기반의 클래스로 정의
이러한 클래스를 가리켜 서블릿(Servlet)이라 한다*/
package mall.product;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.PrintWriter;
import java.io.IOException;

// http://localhost:8282/클래스 직접 호출 불가(간접매핑에 의해 가능)
public class ProductController extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//클라이언트의 웹 브라우저에 출력될 문자열을 스트림으로 준비해보자
		PrintWriter out=response.getWriter();
		out.print("My mall application!");
	}
		
}
