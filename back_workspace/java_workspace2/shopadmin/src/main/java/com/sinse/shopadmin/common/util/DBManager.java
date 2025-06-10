package com.sinse.shopadmin.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sinse.shopadmin.common.config.Config;

/*AppMain에서 DB를 핸들링하지 말고, 보다 중립적인 객체에서 Connection을 얻는 것 뿐 아니라,
닫는 것 마저도 **대행**해주는 기능을 보유한 객체 선언*/
public class DBManager {
	private static DBManager instance; 
	private Connection con;

	//아무도 직접 인스턴스를 생성하지 못하게 생성자의 접근제한을 막아버린다
	private DBManager() { 
		try {
			// 1.드라이버 로드
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2.접속
			con = DriverManager.getConnection(Config.url, Config.id, Config.pwd);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static DBManager getInstance() {
		//만일 인스턴스가 존재하지 않으면, 이 메서드에서 인스턴스 생성해줌
		if(instance==null) {
			instance=new DBManager();
		}
		return instance;
	}

	// getter
	public Connection getConnection() {
		return con;
	}

	// DB관련된 자원을 해제하는 메서드
	public void release(Connection con) { //connection 해제
			try {
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	public void release(PreparedStatement pstmt) { // DML(insert, update, delete) 수행 시
		try {
			if (pstmt != null) pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// SELECT 수행 후 ResultSet + PreparedStatement 해제
	public void release(ResultSet rs, PreparedStatement pstmt) { // select 수행시
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	// 모든 자원 해제 (Connection까지 닫을 경우)
	public void release(ResultSet rs, PreparedStatement pstmt,  Connection con) { // select 수행시
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
