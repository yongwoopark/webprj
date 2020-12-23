package com.newlecture.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class MySecurityFilter implements Filter {
	
	private final static String[] authURLs = {
			"/admin/",
			//"/member/login",
			"/member/index"
	};
	
	//private final static String[] adminURLs = {"/admin/"};
	//private final static String[] usersURLs = {"/user/", ""};

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		
		
		String uri = request.getRequestURI();
		// http://localhost:8080/member/login
		
		// 익명이 사용할 수 있는 URL
		// 인증이 필요한 URL
		
//		url:http://localhost:8080/admin/board/notice/list
//	    uri:/admin/board/notice/list
		
		HttpSession session  = request.getSession();
		//   /admin/*
		//   /member/home
		
		// 인증이 필요한 URL을 요청했나?
		boolean requireAuth = false;
		
		for(String authUrl : authURLs)
			if(uri.contains(authUrl)) {
				requireAuth = true;
				break;
			}
		
		// 인증이 필요한데 인증이 되어 있지 않다면
		if(requireAuth && session.getAttribute("uid") == null) {
			response.sendRedirect("/member/login?return-url="+uri);
			return;
		}
		
		// role : user 
		
		
//		// 권한이 있는지 검사
//		if(!session.getAttribute("role").equals("admin")) {
//			response.sendRedirect("/error/error?errorNo=403");
//			return;
//		}
		
		chain.doFilter(request, response);
		
		
	}
	
}
