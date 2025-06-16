package db;
/*MySQL DB연동*/
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;

class DBTest{
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement pstmt=null; //닫아야하니까 올림
		
		String mysqlDriver="com.mysql.cj.jdbc.Driver";
		String mysqlUrl="jdbc:mysql://localhost:3306/dev";
		String mysqlId="root";
		String mysqlPass="1234";
		
		String oracleDriver="oracle.jdbc.driver.OracleDriver";
		String oracleUrl="jdbc:oracle:thin:@localhost:1521:XE";
		String oracleId="java";
		String oraclePass="1234";
	
		//제어하기를 원하는 드라이버 먼저 로드 (JVM-Method 영역)
		try{
			//1단계) 드라이버 로드
			Class.forName(oracleDriver);
			System.out.println("드라이버 로드 성공");
			
			//2단계) 접속
			String url=oracleUrl;
			String id=oracleId;
			String pass=oraclePass;
			//Connection이란 접속 성공 후, 그 접속 정보를 가진 인터페이스
			//이 객체가 null이면 접속 실패
			con=DriverManager.getConnection(url, id, pass);
			if(con==null){
				System.out.println("접속 실패ㅡ.ㅡ");
			}else{
				System.out.println("접속 성공 ^.^");
				//접속이 성공된 이후, 원격지의 DB서버에 SQL문을 네트워크를 통해 전송
				//String sql="insert into member3(uid, pwd, email) values('Scott', '3333', 'scott@daum.net')";
				String sql="insert into member3(member3_id, id, pwd, email)";
				sql=sql+" values(seq_member3.nextval, 'James', '2222', 'james@gmail.com')";
				//Java DataBase Connectivity - 자바 데이터베이스 연동 기술 및 지원 패키지(java.sql 패키지에서...)
				//JDBC에서 쿼리문 수행을 담당하는 인터페이스는 PreparedStatement
				pstmt = con.prepareStatement(sql); 
				//접속 성공된 이후에 쿼리문 수행이 가능하므로 pstmt객체의 인스턴스는
				//Connection 인터페이스로부터 얻어야함
				
				//준비된 문장으로 쿼리 실행
				int result=pstmt.executeUpdate(); //DML수행시 이 메서드 0이나오면 실패
				if(result>0){
					System.out.println("등록 성공!");
				}else{
					System.out.println("등록 실패");
				}
			}
		}catch(ClassNotFoundException e){
			System.out.println("드라이버를 찾을 수 없습니다. 확인해주세요.");
		}catch(SQLException e){
			e.printStackTrace();//에러 순서를 스택 순서로 출력해줘
		}finally{
			/*DB와 스트림과 같은 자원을 차지하는 기술은 사용 후 반드시 닫기!
			안닫으면 메모리를 계속 확보하고 있음
			만일 웹 서버에 닫지 않은 코드가 올라간다면.. 동시 자원이 다수생성*/
			if(pstmt!=null) 
				try{
					pstmt.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			if(con!=null) 
				try{
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
		}	
	}
}
