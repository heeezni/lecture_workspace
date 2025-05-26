package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*select 문 수행하기*/
public class SelectTest {

	public static void main(String[] args) {
		String url="jdbc:mysql://localhost:3306/dev"; //암기
		String user="java";
		String pwd="1234";
		Connection con=null; //finally에서 닫기 위해서는 try 지역변수 X
		PreparedStatement pstmt=null; //쿼리 수행 객체. 쿼리문 마다 1:1대응
														//3개의 쿼리를 수행할 경우 3개 만듦
		ResultSet rs=null;
		//select version
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Load Success");
			
			//접속
			//다른 언어와 달리 Connection 객체는 접속이 성공되면, 메모리에 올라오는 결과물
			//접속 정보를 가진 객체임! 접속 시도 객체가 아님!
			con = DriverManager.getConnection(url, user, pwd);
			
			if(con!=null) {
				System.out.println("접속 성공!");
				
				String sql="select * from emp";
				pstmt=con.prepareStatement(sql); //pstmt 객체 생성

				//쿼리 실행(DML=insert, update, delete / DDL=create,drop, alter)
				//DML, DDL :executeUpdate()
				//select문 : executeQuery() → ResultSet반환
				rs=pstmt.executeQuery();
				
				
				//최초에 rs를 생성한 시점에는 커서가 첫번 째 레코드보다 위에 위치
				while(rs.next()) {  //커서 한 칸 전진

					String ename=rs.getString("ename"); //현재 커서가 위치한 곳에서의 ename 컬럼 값을 가져옴
					int sal=rs.getInt("sal");
					String job=rs.getString("job");
					String hiredate=rs.getString("hiredate");
					
					System.out.println("ename="+ename+", sal="+sal+", job="+job+", hiredate="+hiredate);
				}
				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs !=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt !=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con !=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
