package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.ShopCart;

public class ShopCartDao extends BaseDao{

	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	
	/**
	 * 添加购物车
	 * 入口：ShopCart
	 * 出口：int
	 * */
	public void addShopCart(ShopCart sc){
		
		String sqll="select * from t_shopcart where bname=? and username=?";
		conn=this.getConnection();
		try {
			pst=conn.prepareStatement(sqll);
			pst.setString(1, sc.getBname());
			pst.setString(2, sc.getUsername());
			rs=pst.executeQuery();
			if(rs.next()){
				int num=rs.getInt("num")+sc.getNum();
				String sqlup="update t_shopcart set num=? where bname=?";
				Object[] arr={num,sc.getBname()};
				 this.excuteSql(sqlup, arr);
			}else{
				String sql="insert into t_shopcart values(null,?,?,?,?,?)";
				Object[] arr={sc.getBname(),sc.getPrice(),sc.getNum(),sc.getImg(),sc.getUsername()};
				this.excuteSql(sql, arr);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据用户查询所有购物车
	 * 入口：用户名
	 * 出口：list
	 * */
	public List<ShopCart> getShopCartByUnaem(String uname){
		
		List<ShopCart> list=new ArrayList<ShopCart>();
		
		String sql="select * from t_shopcart where username=?";
		conn=this.getConnection();
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, uname);
			rs=pst.executeQuery();
			while(rs.next()){
				ShopCart sc=new ShopCart();
				sc.setId(rs.getInt(1));
				sc.setBname(rs.getString(2));
				sc.setPrice(rs.getDouble(3));
				sc.setNum(rs.getInt(4));
				sc.setImg(rs.getString(5));
				sc.setUsername(uname);
				list.add(sc);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pst, rs);
		}
		
		return list;
	}
	/**
	 * 根据id查询购物车
	 * 入口：用户名
	 * 出口：list
	 * */
	public ShopCart getShopCartById(int id){
		
		ShopCart sc=null;
		
		String sql="select * from t_shopcart where id=?";
		conn=this.getConnection();
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs=pst.executeQuery();
			if(rs.next()){
			    sc=new ShopCart();
				sc.setId(rs.getInt(1));
				sc.setBname(rs.getString(2));
				sc.setPrice(rs.getDouble(3));
				sc.setNum(rs.getInt(4));
				sc.setImg(rs.getString(5));
				sc.setUsername(rs.getString(6));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAll(conn, pst, rs);
		}
		
		return sc;
	}
	
	/**
	 * 根据id修改书本数量
	 * 入口：id
	 * 出口：int
	 * */
	public int upBookNum(int id,int num){
		
		String sql="update t_shopcart set num=? where id=?";
		Object[] arr={num,id};
		return this.excuteSql(sql, arr);
	}
	
	/**
	 * 根据id删除书
	 * 入口：id
	 * 出口：int
	 * */
	public int delCartBook(int id){
		
		String sql="delete from t_shopcart where id=?";
		Object[] arr={id};
		return this.excuteSql(sql, arr);
	}
	

	
	
}
