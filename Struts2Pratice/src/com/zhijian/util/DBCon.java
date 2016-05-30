package com.zhijian.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBCon {
	public Connection conn;
	public Statement stmt;
	public ResultSet rs;
	
	public DBCon() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/test?userUnicode=true&characterEncoding=utf-8";
		this.conn = DriverManager.getConnection(url, "root", "root");
	}
	
	public void connect()throws SQLException{
		stmt = conn.createStatement();
		stmt.execute("USE test");
	}
	
	public void disconnest(){
		try {
			if(conn != null){
				conn.close();
			}
			
			if(stmt != null){
				stmt.close();
			}
			
			if(rs != null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
