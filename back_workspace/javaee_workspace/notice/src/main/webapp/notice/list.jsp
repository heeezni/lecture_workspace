<%@page import="com.sinse.notice.model.Notice"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html; charset=UTF-8"%>
<!-- http://localhost:8282/notice/list.jsp로 직접 접근 가능 -->
<%!// !를 선언하면, 선언부라 하여 멤버변수와 멤버메서드를 작성할 수 있다.
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/shop";
	String user = "shop";
	String pwd = "1234";

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	public void connect() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}%>
<!-- JSP란? Java Server Page 
자바기술로 만든 서버 측에서 실행되는 페이지
(서블릿과 목적이 같음)
-->

<%
//장차, 이 jsp가 서블릿으로 변경될 때, service() 메서드의 영역이 된다
//따라서 개발자는 스크립틀릿 영역에 요청을 처리하는 코드를 작성하면 된다
connect(); //접속 

//아래의 변수 out은 개발자가 현재 코드에서 선언한 적이 없음에도 불구하고 그 기능을 제대로 수행한다
//개발자가 정의하지 않아도 톰캣 컨테이너가 jsp내에서 기본적으로 사용할 수 있도록
//미리 생성해놓고 개발에 사용될 수 있도록 지원하는 jsp의 객체들을 가리켜 내장객체라고 한다
//built-in object
//out은 문자 기반의 출력 스트림!!
out.print("접속 객체는 " + con);

//레코드가져오기
pstmt = con.prepareStatement("select * from notice order by notice_id desc");
rs = pstmt.executeQuery(); //쿼리 실행 및 표 반환

List<Notice> list = new ArrayList();

while (rs.next()) {
	Notice notice = new Notice();
	notice.setNotice_id(rs.getInt("notice_id"));
	notice.setTitle(rs.getString("title"));
	notice.setWriter(rs.getString("writer"));
	notice.setRegdate(rs.getString("regdate"));
	notice.setContent(rs.getString("content"));
	notice.setHit(rs.getInt("hit"));

	list.add(notice);
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 100%;
	border: 1px solid #ddd;
}

th, td {
	text-align: left;
	padding: 16px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}
a {text-decoration : none}

button[type=button] {
	background-color: #04AA6D;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

button[type=button]:hover {
	background-color: #45a049;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	/*JQuery - $(누구를).어떻게()
	JQuery의 목적: 코드의 간결화
			요즘 대세 : 바닐라 javascript (순수 js) JQuery없이
	*/
	$(()=>{
		$("button").click(e=>{
			//서버에 재접속하여 글쓰기 폼 요청
			location.href="/notice/write.html"; //<a>태그를 자바스크립트로 처리할 경우. get방식임
		});
	});
</script>
</head>
<body>
	<table>
		<tr>
			<th>No.</th>
			<th>제목</th>
			<th>작성자</th>
			<th>등록일</th>
			<th>조회수</th>
		</tr>
		<%for(Notice notice : list){%> 
		<tr>
			<td>0</td>
			<td><a href="/notice/detail.jsp?notice_id=<%=notice.getNotice_id()%>"><%=notice.getTitle() %></a></td>
			<td><%=notice.getWriter() %></td>
			<td><%=notice.getRegdate() %></td>
			<td><%=notice.getHit() %></td>
		</tr>
		<%} %>
		<tr>
			<td colspan="5"><button type="button">글 등록</button></td>
		</tr>
	</table>

</body>
</html>

<%
	rs.close();
	pstmt.close();
	con.close();
%>