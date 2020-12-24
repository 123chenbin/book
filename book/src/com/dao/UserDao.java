package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.User;

public class UserDao extends BaseDao{

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	/**
	 * 根据用户输入查询，数据库中是否存在匹配的用户名
	 * 入口：用户名
	 * 出口：boolean
	 */
	public boolean selectUname(String uname){
		
		String sql = "SELECT * FROM t_user WHERE uname=?";
		
		try {
			//加载驱动，获得连接
			conn = getConnection();
			//预编译
			pstmt = conn.prepareStatement(sql);
			//处理占位符
			pstmt.setString(1, uname);
			rs = pstmt.executeQuery();
			if(rs.next()){
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			closeAll(conn, pstmt, rs);
		}
		return false;
	}
	
	/**
	 * 根据用户输入的登录名和密码，判断是否正确
	 * 入口：uname，upwd
	 * 出口：user
	 */
	
	public User vallogin(String uname,String upwd){
		
		String sql = "SELECT * FROM t_user WHERE uname=? AND upwd=?";
		User user = null;
		try {
			//加载驱动，获得连接
			conn = getConnection();
			//预编译
			pstmt = conn.prepareStatement(sql);
			//处理占位符
			pstmt.setString(1, uname);
			pstmt.setString(2, upwd);
			rs = pstmt.executeQuery();
			if(rs.next()){
				user = new User();
				user.setId(rs.getInt(1));
				user.setUname(rs.getString(2));
				user.setUpwd(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setIs_admin(rs.getInt(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			closeAll(conn, pstmt, rs);
		}
		return user;
	}
	
	/**
	 * 注册后，往数据库插入用户数据
	 * 入口：uname,upwd,email,is_admin=0
	 * 出口：int
	 */
	public int addUser(User user){
		
		String sql = "INSERT INTO t_user VALUES(null,?,?,?,0)";
		Object[] arr = {user.getUname(),user.getUpwd(),user.getEmail()};
		int rows = excuteSql(sql, arr);
		return rows;
	}
	
	/**
	 * 管理员查询所有人员信息
	 * 入口：无
	 * 出口：集合list
	 */
	public List<User> selectAll(){
		List<User> list = new ArrayList<User>();
		User user = null;
		String sql = "SELECT * FROM t_user";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				user = new User();
				user.setId(rs.getInt(1));;
				user.setUname(rs.getString(2));
				user.setUpwd(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setIs_admin(rs.getInt(5));
				
				list.add(user);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			closeAll(conn, pstmt, rs);
		}
		return list;
	}
	
	/**
	 * 管理员添加其他管理员
	 * 入口：uname,upwd,email,is_admin=1
	 * 出口：int
	 */
	public int addAdmin(User user){
		
		String sql = "INSERT INTO t_user VALUES(null,?,?,?,1)";
		Object[] arr = {user.getUname(),user.getUpwd(),user.getEmail()};
		int rows = excuteSql(sql, arr);
		return rows;
	}
	
	
	
	/**
	 * 管理员可以根据用户名，删除用户
	 * 入口：用户名uname
	 * 出口：int
	 */
	public int delUser(String uname){
		
		String sql = "DELETE FROM t_user WHERE uname=?";
		Object[] arr = {uname};
		int rows = excuteSql(sql, arr);
		
		return rows;
	}
	
	/**
	 * 管理员可以设置他人为管理员，改变is_admin的值为1
	 * 入口：uname
	 * 出口：对象
	 */
	public int modManageUser(String uname){
		
		String sql = "UPDATE t_user SET is_admin=1 WHERE uname=?";
		Object[] arr = {uname};
		int rows = excuteSql(sql, arr);
		return rows;
	}
	
	/**
	 * 管理员可以设置管理员为普通用户，改变is_admin的值为0
	 * 入口：uname
	 * 出口：对象
	 */
	public int modUser(String uname){
		
		String sql = "UPDATE t_user SET is_admin=0 WHERE uname=?";
		Object[] arr = {uname};
		int rows = excuteSql(sql, arr);
		return rows;
	}
	
	/**
	 * 根据用户名，查询用户信息
	 * 入口：用户名uname
	 * 出口：对象user
	 */
	public User selectUserByUname(String uname){
		
		User user = new User();
		String sql = "SELECT * FROM t_user WHERE uname=?";
		
		try {
			//加载驱动，获得连接
			conn = getConnection();
			//预编译
			pstmt = conn.prepareStatement(sql);
			//处理占位符
			pstmt.setString(1, uname);
			rs = pstmt.executeQuery();
			if(rs.next()){
				user.setId(rs.getInt(1));
				user.setUname(rs.getString(2));
				user.setUpwd(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setIs_admin(rs.getInt(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			closeAll(conn, pstmt, rs);
		}
		
		return user;
	}
	
	/**
	 * 用户修改个人密码
	 * 入口：uname，upwd
	 * 出口：对象
	 */
	public int modUserPwd(String uname,String upwd){
		
		String sql = "UPDATE t_user SET upwd=? WHERE uname=?";
		Object[] arr = {upwd,uname};
		int rows = excuteSql(sql, arr);
		return rows;
	}
	/**
	 * 用户修改个人邮箱
	 * 入口：uname，email
	 * 出口：对象
	 */
	public int modUserEmail(String uname,String email){
		
		String sql = "UPDATE t_user SET email=? WHERE uname=?";
		Object[] arr = {email,uname};
		int rows = excuteSql(sql, arr);
		return rows;
	}
	
	
	
}
