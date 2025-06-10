package com.sinse.shopadmin.order.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sinse.shopadmin.common.util.DBManager;
import com.sinse.shopadmin.product.model.SubCategory;
import com.sinse.shopadmin.product.model.TopCategory;

public class SubCategoryDAO {

	DBManager dbManager=DBManager.getInstance(); //DB 싱글턴으로 얻어오자
	
	public void selectAll() {
		
	}
	
	//하위 카테고리 중 유저가 선택한 상위 카테고리에 소속된 목록만 가져오기
	public List selectByTop(TopCategory topcategory) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<SubCategory> list=new ArrayList<>();
		
		StringBuffer sql=new StringBuffer();
		sql.append("select * from subcategory where topcategory_id=?");
		
		con=dbManager.getConnection();
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, topcategory.getTopcategory_id());
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				SubCategory subcategory=new SubCategory(); //현재 empty
				subcategory.setSubcategory_id(rs.getInt("subcategory_id")); //rs죽으니까 pk옮기고
				subcategory.setSub_name(rs.getString("sub_name"));//name옮기기
				subcategory.setTopCategory(topcategory); //TopCategory의 인스턴스 
				
				list.add(subcategory); //rs를 대신할 list에 담기
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(rs,pstmt);
		}
		return list;
	}
}
