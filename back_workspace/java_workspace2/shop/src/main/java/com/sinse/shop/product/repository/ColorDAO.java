package com.sinse.shop.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sinse.shop.common.util.DBManager;
import com.sinse.shop.product.model.Color;

// 다른 로직은 포함하면 안되며, 오직 Color테이블에 CRUD만을 수행하는 쿼리수행 객체
// Data Access Object (쿼리 전담 객체)
public class ColorDAO {
	DBManager dbManager=DBManager.getInstance();
	
	//Create = insert문 
	public int insert(Color color) { //우리가 만든 Color 객체
		//쿼리문을 날리려면 뭐가 필요할까? 생각 
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0; //일단 실패라고 해놓고
		
		StringBuffer sql=new StringBuffer();
		sql.append("insert into color(color_name) values(?)");
		
		con=dbManager.getConnection();
		try {
			pstmt=con.prepareStatement(sql.toString());
			
			//바인드 변수값 결정
			pstmt.setString(1, color.getColor_name());
			result=pstmt.executeUpdate(); //DML 수행시, 이 쿼리에 의해 영향을 받은 레코드 수 반환
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(pstmt);
		}
		return result;
	}
	
	//모든 색상 가져오기
	public List<Color> selectAll() {
		//데이터베이스 연동위해 필요한게 뭘까?
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<Color> list=new ArrayList<>(); //rs대체로 담을 List객체
		
		con=dbManager.getConnection();
		StringBuffer sql=new StringBuffer();
		sql.append("select * from color");
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			rs=pstmt.executeQuery(); //표반환
			//rs 죽기 전에 rs가 보유한 데이터를 모델 객체로 옮기자
			//모델은 인스턴스 1건은 레코드 1건 담는다
			while(rs.next()) { //레코드 있는 만큼
				Color color=new Color(); //레코드 1건을 담는 모델 인스턴스
				color.setColor_id(rs.getInt("color_id")); //rs가 가진거 뺏어서 먹기
				color.setColor_name(rs.getString("color_name"));
				list.add(color); //유사배열객체에 push
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(rs, pstmt);
		}
		
		return list;
	}
}
