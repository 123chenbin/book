package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Order;

public class OrderDao extends BaseDao{
	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	/**
	 * 查询最大订单号
	 * */
	public int getMaxOrderno(){
		String sql="SELECT MAX(orderno) FROM t_order ";
		int n=0;
		conn=this.getConnection();
		try {
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			rs.next();
			n=rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			
			this.closeAll(conn, pst, rs);
		}
		return n;
	}
	/**
	 * 添加订单
	 * 
	 * */
	public int addOrder(String name,double totalmoney,String username,String tel,String adress){
		String sql="INSERT INTO t_order VALUES(NULL,?,?,'待审核',NOW(),?,?,?)";
		Object[] arr={name,totalmoney,username,tel,adress};
		return this.excuteSql(sql, arr);
	}
	 /**
	  * 根据登录的用户名查询订单
	  * 入口：用户名
	  * 出口：list集合
	  * */
	
	public List<Order> getOrderByUname(String uname){
		List<Order> list=new ArrayList<Order>();
		Order ord=null;
		String sql="select * from t_order where username=?";
		conn=this.getConnection();
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, uname);
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
		}
		finally{
			this.closeAll(conn, pst, rs);
		}
		return list;
	}
	/**
	 * 根据订单号查询订单
	 * 
	 * */
	public Order getOrderByOno(int orderno){
		Order ord=null;
		
		String sql="select * from t_order where orderno=?";
		conn=this.getConnection();
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, orderno);
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
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			this.closeAll(conn, pst, rs);
		}
		return ord;
	}
	/**
	 * 修改订单的收货人和和订单状态，电话，收货地址
	 * 
	 * */
	public int modOrder(Order ord,int orderno){
		String sql="UPDATE t_order SET uname=?,state=?,tel=?,adress=? WHERE orderno=?";
		Object[] arr={ord.getName(),ord.getState(),ord.getTel(),ord.getAdress(),orderno};
		return this.excuteSql(sql, arr);
		
	}
	/**
	 * 查询所有订单
	 * */
	public List<Order> getAll(){
		List<Order> list=new ArrayList<Order>();
		Order ord=null;
		String sql="select * from t_order";
		conn=this.getConnection();
		try {
			pst=conn.prepareStatement(sql);
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
		}
		finally{
			this.closeAll(conn, pst, rs);
		}
		return list;
		
	}
}
