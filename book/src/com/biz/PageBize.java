package com.biz;

import com.dao.PageDao;
import com.util.PageBean;
import com.dao.BookDao;
public class PageBize {
	
	PageDao dao=new PageDao();
	/**
	 * 封装order的pageBean
	 * 入口：page pageSize
	 * 出口：PageBean 
	 * */
	public PageBean getOrderPageBean(int page , int pageSize,String name){
		PageBean bean=new PageBean();
		bean.setPage(page);
		bean.setPageSize(pageSize);
		bean.setTotalCount(dao.getOrderCount(name));
		//bean.setTotalPage(totalPage);总页数
		bean.setList(dao.getByPage(page, pageSize,name));
		return bean;
	}
	
	
	public PageBean getOrderPageBean(int page , int pageSize){
		PageBean bean=new PageBean();
		bean.setPage(page);
		bean.setPageSize(pageSize);
		bean.setTotalCount(dao.getOrderCount());
		//bean.setTotalPage(totalPage);总页数
		bean.setList(dao.getByPage(page, pageSize));
		return bean;
	}
	
	

	
}
