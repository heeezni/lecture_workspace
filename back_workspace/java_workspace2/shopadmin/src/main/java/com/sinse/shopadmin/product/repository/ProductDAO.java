package com.sinse.shopadmin.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sinse.shopadmin.common.util.DBManager;
import com.sinse.shopadmin.product.model.Product;
import com.sinse.shopadmin.product.model.SubCategory;

//Product 테이블에 대한 CRUD만을 수행 - DB작업코드만 작성해야함
public class ProductDAO {
	DBManager dbManger = DBManager.getInstance();

	public int insert(Product product) {
		// 상품입력 폼의 값을 담고있는 Product 모델을 출력해보기
		System.out.println(product.getProduct_name());
		System.out.println(product.getBrand());
		System.out.println(product.getPrice());
		System.out.println(product.getDiscount());
		System.out.println(product.getIntroduce());
		System.out.println(product.getDetail());
		System.out.println("SubCategory의 PK는 " + product.getSubcategory().getSubcategory_id()); // 2중으로 꺼내기

		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0; // 쿼리 실행 성공 여부 결정짓는 변수

		con = dbManger.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("insert into product(product_name, brand, price, discount, introduce, detail, subcategory_id)");
		sql.append(" values(?, ?, ?, ?, ?, ?, ?)");

		try {
			pstmt = con.prepareStatement(sql.toString());

			// 모델객체에 채워진 데이터를 꺼내서 바인드 변수에 대입하기
			pstmt.setString(1, product.getProduct_name());
			pstmt.setString(2, product.getBrand());
			pstmt.setInt(3, product.getPrice());
			pstmt.setInt(4, product.getDiscount());
			pstmt.setString(5, product.getIntroduce());
			pstmt.setString(6, product.getDetail());
			pstmt.setInt(7, product.getSubcategory().getSubcategory_id());

			// 쿼리수행
			result = pstmt.executeUpdate(); // DML실행

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManger.release(pstmt);
		}

		return result;
	}

	// 방금 수행한 insert에 의해 증가된 PK의 최신값 얻기
	// 나의 세션 내에서 증가 된 것만 가져오기!!!! (select last_insert_id() 함수)
	// 절대 max()는 사용하면 안됨 -> 다른 유저 계정에 의한 증가값도 반환해버리기 때문 (나의 세션에 국한 x)
	public int selectRecentPk() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int pk = 0;

		con = dbManger.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("select last_insert_id() as product_id");

		try {
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery(); // 쿼리 실행 및 결과표 반환

			while (rs.next()) { // 조회된 결과가 있다면
				pk = rs.getInt("product_id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManger.release(rs, pstmt);
		}

		return pk;
	}

}
