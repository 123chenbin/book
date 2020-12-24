package com.biz;

import com.dao.BookDao;
import com.util.PageBean;

public class pagePro {
	BookDao dao=new BookDao();
	public PageBean getProPg(int page , int pageSize){
		PageBean bean=new PageBean();
		bean.setPage(page);
		bean.setPageSize(pageSize);
		bean.setTotalCount(dao.TotalCount());
		//bean.setTotalPage(totalPage);总页数
		bean.setList(dao.getProBypage(page, pageSize));
		return bean;
	}

		
		public PageBean getProPgByName(int page , int pageSize,String name){
			PageBean bean=new PageBean();
			bean.setPage(page);
			bean.setPageSize(pageSize);
			bean.setTotalCount(dao.TotalCountByName(name));
			//bean.setTotalPage(totalPage);总页数
			bean.setList(dao.getProBypageByname(page, pageSize, name));
			return bean;
		}
	
	
}
