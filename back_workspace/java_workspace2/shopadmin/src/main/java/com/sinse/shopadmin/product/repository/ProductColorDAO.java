package com.sinse.shopadmin.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.sinse.shopadmin.common.util.DBManager;
import com.sinse.shopadmin.product.model.ProductColor;

public class ProductColorDAO {
	DBManager dbmanager=DBManager.getInstance();
	
	// 특정 상품이 가지고 있는 색상들을 입력 
	public int insert(ProductColor productColor) { //ProductColor가 이미 Product와 Color를 보유
		Connection con =null;
		PreparedStatement pstmt=null;
		int result=0;
		
		con=dbmanager.getConnection();
		StringBuffer sql=new StringBuffer();
		sql.append("insert into product_color(product_id, color_id) values(?,?)");
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, productColor.getProduct().getProduct_id()); //상품의 pk
			pstmt.setInt(2, productColor.getColor().getColor_id()); //색상의 pk
			result=pstmt.executeUpdate(); //DML 실행
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbmanager.release(pstmt);
		}
		return result;
	}
}
