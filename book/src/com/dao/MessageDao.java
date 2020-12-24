package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Message;

public class MessageDao extends BaseDao{
	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	/**
	 * 根据用户名和消息的状态查询消息的条数
	 * name state
	 * */
	public int getCountByNameState(String name){
		int n=0;
		String sql="SELECT COUNT(2) FROM t_message WHERE username=? AND adstate=1";
		conn=this.getConnection();
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, name);
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
	 * 根据用户名和消息的状态查询消息
	 * name state
	 * */
	public List<Message> getCountByName(String name){
		 List<Message> list=new ArrayList<Message>();
		 Message mes=null;
		String sql="SELECT * FROM t_message WHERE username=?";
		conn=this.getConnection();
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, name);
			rs=pst.executeQuery();
			while(rs.next()){
				mes=new Message();
				mes.setId(rs.getInt(1));
				mes.setName(rs.getString(2));
				mes.setUmessage(rs.getString(3));
				mes.setState(rs.getInt(4));
				mes.setAdmessage(rs.getString(5));
				mes.setAdstate(rs.getInt(6));
				list.add(mes);
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
	 * 根据用户名修改管理员消息的状态
	 * */
	public void modMsta(String name){
		String sql="UPDATE t_message SET adstate=0 WHERE username=?";
		Object[] arr={name};
		this.excuteSql(sql, arr);
	}
	/**
	 * 增加消息
	 * */
	public void addMessage(String name,String message){
		String sql="INSERT INTO t_message VALUES(NULL,?,?,1,NULL,1)";
		Object[] arr={name ,message};
		this.excuteSql(sql, arr);
	}
	
	////////////////管理员的方法///////////////////
	
	/**
	 * 管理员根据用户的消息状态查询消息条数
	 * */
	public int adGetCountByNState(String name){
		int n=0;
		String sql="SELECT COUNT(2) FROM t_message WHERE username=? and ustate=1";
		conn=this.getConnection();
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, name);
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
	 * 管理员根据用户的消息状态查询消息总条数
	 * */
	public int adGetCountBy(){
		int n=0;
		String sql="SELECT COUNT(2) FROM t_message WHERE  ustate=1";
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
	 * 根据用户名消息的状态查询消息
	 *  state
	 * */
	public List<Message> getMgByUname(String name){
		 List<Message> list=new ArrayList<Message>();
		 Message mes=null;
		String sql="SELECT * FROM t_message WHERE username=?";
		conn=this.getConnection();
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1,name);
			rs=pst.executeQuery();
			while(rs.next()){
				mes=new Message();
				mes.setId(rs.getInt(1));
				mes.setName(rs.getString(2));
				mes.setUmessage(rs.getString(3));
				mes.setState(rs.getInt(4));
				mes.setAdmessage(rs.getString(5));
				mes.setAdstate(rs.getInt(6));
				list.add(mes);
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
	 * 增加消息
	 * */
	public void adAddMessage(String name,String message){
		String sql="INSERT INTO t_message VALUES(NULL,?,NULL,0,?,1)";
		Object[] arr={name ,message};
		this.excuteSql(sql, arr);
	}
	/**
	 * 根据用户名修改用户消息的状态
	 * */
	public void modAdminMsta(String name){
		String sql="UPDATE t_message SET ustate=0 WHERE username=?";
		Object[] arr={name};
		this.excuteSql(sql, arr);
	}
}
