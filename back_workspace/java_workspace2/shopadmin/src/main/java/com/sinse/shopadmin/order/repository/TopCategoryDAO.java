package com.sinse.shopadmin.order.repository;

/*Java EE개발에서는, 개발자들 간 협업을 위해 정해진 규칙에 따라 코드를 작성해야한다.
 특히 DB에서 테이블이 하나 정의되면, java개발자는 이 테이블에 대해 1:1 대응하는
 모델객체 + 이 테이블에 대한 CRUD(Create=insert, Read=select, Update, Delete)를 무조건 만든다
 ex) 협업관계에 있는 같은 팀 프로젝트 구성원인  A,B 있다고 가정할 때,
 Product 테이블에 대해서 A가 모델 & DAO를 만들면, B는 그 객체를 가져가 사용하면 됨 (중복정의x)*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sinse.shopadmin.common.util.DBManager;
import com.sinse.shopadmin.product.model.TopCategory;

/*이 코드는 오직 데이터베이스관련 코드만 들어있어야 함
 Swing 제어 등의 디자인 코드가 절대로 들어있으면 안됨!
 이 객체는 MVC에서 Model영역(로직 객체영역)을 담당하므로 View가 섞이면 안됨
 
누구나 쓸 수 있게 중립적이여야함(M랑 V랑 섞이면 안된다)*/
public class TopCategoryDAO {
	//DBManger는 싱글턴 패턴으로 정의되어 있으므로 new 불가
	//따라서 static 메서드로 ㄱㄱ
	DBManager dbManager=DBManager.getInstance();
	//dbManager는 인스턴스 변수이므로, 누군가가 new TopCategoryDAO()호출 시
	//초기화 되면서 DBManger의 인스턴스 값으로 채워진다
	
	//TopCategory의 모든 레코드 가져오기
	public ArrayList selectAll() { 
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList list=new ArrayList(); //JS의 배열과 아주 흡사, 크기가 고무줄
		
		con=dbManager.getConnection();
		
		try {
			StringBuffer sql=new StringBuffer();
			sql.append("select * from topcategory");
			pstmt=con.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();
		
			
			//만일 배열로 rs의 데이터를 담게되면, 배열의 크기를 명시해야하는 이유로
			//rs.last(), getRow(), rs.beforeFirst() ... 번거로움
			/*Collection framework : 객체를 모아서 처리하는 데 유용한 API 지원하는 패키지
			1) 순서 있는 모습 List, Queue
			2) 순서 없는 모습 Set, Map*/
			
			while(rs.next()) {
				TopCategory topcategory=new TopCategory();
				topcategory.setTopcategory_id(rs.getInt("topcategory_id")); //pk
				topcategory.setTop_name(rs.getString("top_name"));			
				list.add(topcategory); //js의 push()와 동일
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(rs, pstmt);			
		}
		return list;
	}
	
	//TopCategory의 레코드 1건 가져오기
	public void select() {
		
	}
	
	//1건 입력
	public void insert() {
		
	}
	
	//1건 수정
	public void update() {
		
	}
	
	//1건 삭제
	public void delete() {
		
	}
	
}
