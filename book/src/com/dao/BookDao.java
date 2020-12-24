package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Book;




public class BookDao extends BaseDao{
         
			Connection conn=null;
			PreparedStatement pst=null;
			ResultSet rs=null;
			
			/**
			 * 增加数据的方法
			 */
	public int addBook(Book b){
	String sql="INSERT INTO t_product VALUES(null,?,?,?,?,?)";
	Object [] arr={b.getBname(),b.getPrice(),b.getStore(),b.getImg(),b.getAuthor()};
	return this.excuteSql(sql, arr);
		}
	
	 /**
	  * 列表书籍
	  */
	
	//列表商品
	
		public List<Book> SelectAll(){
			List<Book> li=new ArrayList<Book> ();
			
			String sql="select * from t_product ";
			conn=this.getConnection();
			
			try {
				pst=conn.prepareStatement(sql);
				rs=pst.executeQuery();
				while(rs.next()){
					Book p=new Book();
					p.setId(rs.getInt(1));
					p.setBname(rs.getString(2));
					p.setPrice(rs.getInt(3));
					p.setStore(rs.getInt(4));
					p.setImg(rs.getString(5));
					p.setAuthor(rs.getString(6));
					li.add(p);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return li;
		}
	 	
	     /**
	      *删除后台列表商品
	      */
		public int delBook(int id){
			  String sql="delete from t_product where id=?";
			  Object [] arr={id};
			  return this.excuteSql(sql, arr);
			
		}
		/**
		 * 根据id更新商品库存
		 * 
		 * */
		public int upBookNum(int id){
			Book b=getProById(id);
			int num=b.getStore()-1;
			String sql="update t_product set store=? where id=?";
			Object[] arr={num,id};
			return this.excuteSql(sql, arr);
		
		}
		
		
		/**
		 * 根据书名更新商品库存
		 * 
		 * */
		public int upBookNumByName(String name,int num){
			
			Book b=getProByName(name);
			int sum=b.getStore()+num;
			String sql="update t_product set store=? where bname=?";
			Object[] arr={sum,name};
			return this.excuteSql(sql, arr);
		
		}
		
		/**
		 * 通过商品的name查找一个商品
		 * 
		 */
		public Book getProByName(String name){
			Book b=null;
			String sql="SELECT*FROM t_product WHERE bname=?";
			try {
				conn=this.getConnection();
				pst=conn.prepareStatement(sql);
				
				pst.setString(1, name);
				rs=pst.executeQuery();
				if(rs.next())
				{
				 b=new Book();
					b.setId(rs.getInt(1));
					b.setBname(rs.getString(2));
					b.setPrice(rs.getInt(3));
					b.setStore(rs.getInt(4));
					b.setImg(rs.getString(5));
					b.setAuthor(rs.getString(6));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch 
				e.printStackTrace();
			}finally{
				this.closeAll(conn, pst, rs);
			}
			return b;
		}
		
		
		/**
		 * 通过商品的id查找一个商品
		 * 
		 */
		public Book getProById(int id){
			Book b=null;
			String sql="SELECT*FROM t_product WHERE id=?";
			try {
				conn=this.getConnection();
				pst=conn.prepareStatement(sql);
				
				pst.setInt(1, id);
				rs=pst.executeQuery();
				if(rs.next())
				{
				 b=new Book();
					b.setId(rs.getInt(1));
					b.setBname(rs.getString(2));
					b.setPrice(rs.getInt(3));
					b.setStore(rs.getInt(4));
					b.setImg(rs.getString(5));
					b.setAuthor(rs.getString(6));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch 
				e.printStackTrace();
			}finally{
				this.closeAll(conn, pst, rs);
			}
			return b;
		}
		
		/**
		 * 后台修改
		 */
		
		public int mod(Book b){
			String sql="update t_product set bname=?,price=?,store=?,author=?,img=? where id=?";
			Object [] arr={b.getBname(),b.getPrice(),b.getStore(),b.getAuthor(),b.getImg(),b.getId()};
			return this.excuteSql(sql, arr);
					
			
		}
		
		
		/**
		 * 通过名字查询数据是否有添加的书已存在
		 */
		
		public boolean CheckBook(String name){
			
			String sql="select * from t_product where bname=?";
			conn=this.getConnection();
			try {
						pst=conn.prepareStatement(sql);
						pst.setString(1, name);
						 rs= pst.executeQuery();
					 	    if(rs.next())
					 	   return true;
					 	    } catch(Exception e){
					 	    e.printStackTrace();
					 	    }
					 	   
					 	    finally{
					 	 	   
					 			try {
					 				 if(pst!=null)
					 				pst.close();
					 				 if(conn!=null)
					 				conn.close();
					 			} catch (SQLException e) {
					 				// TODO Auto-generated catch block
					 				e.printStackTrace();
					 			}
					 	    }
					 			
					 	    return false;
					  }
		
		
		/**
		 * 前台首页模糊查询
		 */
		public List<Book> SelectMohu(String name){
			List<Book> li=new ArrayList<Book>();
			String sql="SELECT * FROM t_product WHERE bname LIKE ? OR author LIKE ? ";
			conn=this.getConnection();
			try {
				pst=conn.prepareStatement(sql);
				pst.setString(1, "%"+name+"%");
				pst.setString(2, "%"+name+"%");
				rs=pst.executeQuery();
				while(rs.next()){
					Book b=new Book();
					b.setId(rs.getInt(1));
					b.setBname(rs.getString(2));
					b.setPrice(rs.getDouble(3));
					b.setStore(rs.getInt(4));
					b.setImg(rs.getString(5));
					b.setAuthor(rs.getString(6));
					li.add(b);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				this.closeAll(conn, pst, rs);
			}
			return li;
		}
		 /**
		    * 查找总条数的方法
		    * 模糊查询
		    */
			public int TotalCountByName(String name){
				String sql="SELECT  COUNT(3) FROM t_product where bname like ? OR author LIKE ?";
				int n=0;
				conn=this.getConnection();
				try {
					pst=conn.prepareStatement(sql);
					pst.setString(1, "%"+name+"%");
					pst.setString(2, "%"+name+"%");
					rs=pst.executeQuery();
					rs.next();
				     n=rs.getInt(1);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					this.closeAll(conn, pst, rs);
				}
				return n;
			}
		
			/**
			 * 根据输入的一页显示的条数，页码查找从
			 *入口page，pagesize 
			 */
			
			public List<Book> getProBypageByname(int page,int pagesize,String name){
				List <Book> list=new ArrayList<Book>();
				String sql="SELECT * FROM t_product where bname like ? OR author LIKE ? LIMIT ?,?";
				conn=this.getConnection();
				try {
				pst=conn.prepareStatement(sql);
				int begin=(page-1)*pagesize;
				pst.setString(1, "%"+name+"%");
				pst.setString(2, "%"+name+"%");
				pst.setInt(3, begin);
				pst.setInt(4, pagesize);
				rs=pst.executeQuery();
				while(rs.next()){
					Book b=new Book();
					b.setId(rs.getInt(1));
					b.setBname(rs.getString(2));
					b.setPrice(rs.getDouble(3));
					b.setStore(rs.getInt(4));
					b.setImg(rs.getString(5));
					b.setAuthor(rs.getString(6));
					list.add(b);
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
	    * 查找总条数的方法
	    * 
	    */
		public int TotalCount(){
			String sql="SELECT  COUNT(3) FROM t_product";
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
			}finally{
				this.closeAll(conn, pst, rs);
			}
			return n;
		}
		
		/**
		 * 根据输入的一页显示的条数，页码查找从
		 *入口page，pagesize 
		 */
		
		public List<Book> getProBypage(int page,int pagesize){
			List <Book> list=new ArrayList<Book>();
			String sql="SELECT * FROM t_product LIMIT ?,?";
			conn=this.getConnection();
			try {
			pst=conn.prepareStatement(sql);
			int begin=(page-1)*pagesize;
			pst.setInt(1, begin);
			pst.setInt(2, pagesize);
			rs=pst.executeQuery();
			while(rs.next()){
				Book b=new Book();
				b.setId(rs.getInt(1));
				b.setBname(rs.getString(2));
				b.setPrice(rs.getDouble(3));
				b.setStore(rs.getInt(4));
				b.setImg(rs.getString(5));
				b.setAuthor(rs.getString(6));
				list.add(b);
			}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				this.closeAll(conn, pst, rs);
			}
			return list;
		}
		
		
		
		
}
