package com.newlecture.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/*")
public class DispatcherServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String url = "";
		if(? == "/index")
			url = new IndexController().service(.req..);
		if(? == "/notice/list")
			url = new IndexController().service(.req..);
		if(? == "/index")
			url = new IndexController().service(.req..);
		
		if(? == "/index")
			url = new IndexController().service(.req..);
		
		req.getRequestDispatcher(url).forward(arg0, arg1);
		
		
		
	}
}
