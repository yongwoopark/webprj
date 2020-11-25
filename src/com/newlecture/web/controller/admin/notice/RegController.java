package com.newlecture.web.controller.admin.notice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/notice/reg")
public class RegController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, 
									HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String title = request.getParameter("title");
		
		if(title != null)
			System.out.printf("title is %s\n", title);
		
		//String[] titles = request.getParameterValues("title"); 
		String file = request.getParameter("file");
		String[] foods = request.getParameterValues("food");
		
		//for(int i=0; i<titles.length; i++)
		//	System.out.printf("title is %s\n", titles[i]);
		
		for(int i=0; i<foods.length; i++)
			out.printf("음식 is %s\n", foods[i]);
		
		
		
		
	}
}
