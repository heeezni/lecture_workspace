package com.sinse.shopadmin.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.sinse.shopadmin.common.util.DBManager;
import com.sinse.shopadmin.product.model.ProductImg;

//하나의 제품에 딸려있는 이미지 등록
public class ProductImgDAO {
	DBManager dbManager = DBManager.getInstance();
	
	public int insert(ProductImg productImg) {
		
		Connection con=null;
		PreparedStatement pstmt = null;
		int result=0;
		con=dbManager.getConnection();
		StringBuffer sql=new StringBuffer();
		sql.append("insert into product_img(filename, product_id) values(?,?)");
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, productImg.getFilename());
			pstmt.setInt(2, productImg.getProduct().getProduct_id());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
