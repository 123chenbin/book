package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.OrderDetail;
import com.entity.ShopCart;

public class OrderDetailDao extends BaseDao{
	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	/**
	 * 根据订单id查询对应的商品
	 * 入口oid
	 * 出口list集合OrderDetail对象
	 * */
	public List<OrderDetail> getOrdtilByOid(int oid){
		List<OrderDetail> list=new ArrayList<OrderDetail>();
		OrderDetail odd=null;
		String sql="SELECT * FROM t_orderdetail WHERE oid=?";
		conn=this.getConnection();
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, oid);
			rs=pst.executeQuery();
			while(rs.next()){
				odd=new OrderDetail();
				odd.setId(rs.getInt(1));
				odd.setPid(rs.getInt(2));
				odd.setOid(rs.getInt(3));
				odd.setNum(rs.getInt(4));
				odd.setPrice(rs.getDouble(5));
				odd.setImg(rs.getString(6));
				odd.setPname(rs.getString(7));
				list.add(odd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	/**
	 * 添加订单详情
	 * */
	public int addOrderDetail(int orderno,ShopCart sp){
		String sql="INSERT INTO t_orderdetail VALUES(NULL,?,?,?,?,?,?)";
		Object[] arr={sp.getId(),orderno,sp.getNum(),sp.getPrice(),sp.getImg(),sp.getBname()};
		return this.excuteSql(sql, arr);
	}
}
