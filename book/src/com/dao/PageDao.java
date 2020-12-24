package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import com.entity.Order;



public class PageDao extends BaseDao{
	Connection con=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	/**
	 * 查询product条数
	 * */
	public int getCount(){
		String sql="SELECT	COUNT(5) FROM product";
		int n=0;
		con=this.getConnection();
		try {
			pst=con.prepareStatement(sql);
			rs=pst.executeQuery();
			rs.next();
			n=rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n;
	}
	/**
	 * 根据page查order数据
	 * 入口；page，pageSize
	 * 出口：List<order>
	 * */
	public List getByPage(int page , int pageSize,String name){
		List list =new ArrayList();
		Order ord=null;
		String sql="SELECT * FROM t_order where username=? LIMIT ?,?";
		con=this.getConnection();
		int beign=(page-1)*pageSize;
		try {
			pst=con.prepareStatement(sql);
			pst.setString(1, name);
			pst.setInt(2, beign);
			pst.setInt(3, pageSize);
			rs=pst.executeQuery();
			while(rs.next()){
				ord=new Order();
				ord.setOrderno(rs.getInt(1));
				ord.setName(rs.getString(2));
				ord.setTotalmoney(rs.getDouble(3));
				ord.setState(rs.getString(4));
				ord.setOrdertime(rs.getString(5));
				ord.setUsername(rs.getString(6));
				ord.setTel(rs.getString(7));
				ord.setAdress(rs.getString(8));
				list.add(ord);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			this.closeAll(con, pst, rs);
		}
		return list;
	}
	public List getByPage(int page , int pageSize){
		List list =new ArrayList();
		Order ord=null;
		String sql="SELECT * FROM t_order LIMIT ?,?";
		con=this.getConnection();
		int beign=(page-1)*pageSize;
		try {
			pst=con.prepareStatement(sql);
			pst.setInt(1, beign);
			pst.setInt(2, pageSize);
			rs=pst.executeQuery();
			while(rs.next()){
				ord=new Order();
				ord.setOrderno(rs.getInt(1));
				ord.setName(rs.getString(2));
				ord.setTotalmoney(rs.getDouble(3));
				ord.setState(rs.getString(4));
				ord.setOrdertime(rs.getString(5));
				ord.setUsername(rs.getString(6));
				ord.setTel(rs.getString(7));
				ord.setAdress(rs.getString(8));
				list.add(ord);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			this.closeAll(con, pst, rs);
		}
		return list;
	}
	
	/**
	 * 根据登录用户名查询order条数
	 * */
	public int getOrderCount(String name){
		String sql="SELECT	COUNT(5) FROM t_order where username=?";
		int n=0;
		con=this.getConnection();
		try {
			pst=con.prepareStatement(sql);
			pst.setString(1, name);
			rs=pst.executeQuery();
			rs.next();
			n=rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n;
	}
	/**
	 * 查询order条数
	 * */
	public int getOrderCount(){
		String sql="SELECT	COUNT(5) FROM t_order";
		int n=0;
		con=this.getConnection();
		try {
			pst=con.prepareStatement(sql);
			rs=pst.executeQuery();
			rs.next();
			n=rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n;
	}
}
