package com.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.dao.BookDao;
import com.entity.Book;

public class modBookAction extends HttpServlet {

	/**
		 * The doGet method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to get.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		ServletContext application=this.getServletContext();
		Book b=new Book();
		FileItemFactory fac=new DiskFileItemFactory();
		ServletFileUpload upload=new ServletFileUpload(fac);
		if(upload.isMultipartContent(request)){
			List<FileItem> list=null;
			try {
				list = upload.parseRequest(request);
			} catch (FileUploadException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for(FileItem item:list){
				if(item.isFormField()){
					String name=item.getFieldName();
					String val=item.getString("utf-8");
					if(name.equals("id"))
						b.setId(Integer.parseInt(val));
					if(name.equals("bname"))
						b.setBname(val);
					if(name.equals("author"))
						b.setAuthor(val);
					if(name.equals("price"))
						b.setPrice(Double.parseDouble(val));
					if(name.equals("store"))
						b.setStore(Integer.parseInt(val));
			}else{
				String path=application.getRealPath("images/book");
				File oldfile=new File(item.getName());
				File savefile=new File(path+"/"+oldfile.getName());
				try {
					item.write(savefile);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				b.setImg("images/book"+"/"+savefile.getName());
			}
			
			}
		}else{
			
		}
		BookDao dao=new BookDao();
		System.out.println(b.getBname()+";"+b.getAuthor());
		dao.mod(b);
		
		response.sendRedirect("BackPagelist");
		out.flush();
		out.close();
	}

}
