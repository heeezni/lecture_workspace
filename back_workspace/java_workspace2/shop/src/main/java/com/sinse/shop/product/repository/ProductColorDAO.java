package com.sinse.shop.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.sinse.shop.common.exception.ProductColorException;
import com.sinse.shop.common.util.DBManager;
import com.sinse.shop.product.model.ProductColor;

public class ProductColorDAO {
	DBManager dbmanager=DBManager.getInstance();
	
	// 특정 상품이 가지고 있는 색상들을 입력 
	public void insert(ProductColor productColor) throws ProductColorException{ //ProductColor가 이미 Product와 Color를 보유
		Connection con =null;
		PreparedStatement pstmt=null;
		
		con=dbmanager.getConnection();
		StringBuffer sql=new StringBuffer();
		sql.append("insert into product_color(product_id, color_id) values(?,?)");
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, productColor.getProduct().getProduct_id()); //상품의 pk
			pstmt.setInt(2, productColor.getColor().getColor_id()); //색상의 pk
			int result=pstmt.executeUpdate(); //DML 실행
			
			if(result<1) {
				throw new ProductColorException("상품의 색상이 등록되지 않았습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ProductColorException("상품의 색상 등록 시 문제가 발생하였습니다.", e);
			
			
		} finally {
			dbmanager.release(pstmt);
		}
	}
}
