package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDao {
	private static final String DRIVER="com.mysql.jdbc.Driver";
	private static final String URL="jdbc:mysql://localhost:3306/books?characterEncoding=utf-8";
	private static final String USERNAME="root";
	private static final String PASSWORD="123456";
	//创建连接
	public Connection getConnection(){
		Connection con=null;
		
		try {
			Class.forName(DRIVER);
			con=DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
	
	//关闭数据库资源
	public void closeAll(Connection con,Statement st,ResultSet rs){
		
		try {
			if(rs!=null)rs.close();
			
			if(st!=null)st.close();
			if(con!=null)con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	/**
	 * 执行增删改的方法
	 * 
	 * */
	public int excuteSql(String sql, Object[] arr){
		int num=0;
		Connection con=null;
		PreparedStatement pst=null;
		
		con=getConnection();
		try {
			pst=con.prepareStatement(sql);
			if(arr!=null){
			int n=1;
			for(Object obj:arr){
				pst.setObject(n,obj);
				n++;
			}
			}
			num=pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			this.closeAll(con, pst, null);
		}
		
		return num;
	}
}
