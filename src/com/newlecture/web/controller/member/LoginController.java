package com.newlecture.web.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.newlecture.web.service.MemberService;

@WebServlet("/member/login")
public class LoginController extends HttpServlet {
	private MemberService service;
	public LoginController() {
		service = new MemberService();
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String returnURL = request.getParameter("return-url");
		request.setAttribute("returnUrl", returnURL);
		
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uid = request.getParameter("uid");
		String pwd =request.getParameter("pwd");
		String returnURL = request.getParameter("return-url");
		
		System.out.printf("uid:%s, pwd:%s\n", uid, pwd);
		
		HttpSession session = request.getSession();		
		// 1. 내가 로그인 상태인가? 확인 방법 
		// 2. 걸려서 왔으면 다시 가야지?
		// 3. 권한과 인증이 같은 건 아니지?
		
		if(service.isValid(uid, pwd)) {
			// 유효 하니까... 유효함을 어딘가에 저장해야 한다.
			session.setAttribute("uid", uid);
			
			session.setAttribute("role", "admin");
			
			if(returnURL != null && !returnURL.equals(""))
				response.sendRedirect(returnURL);
			else
				response.sendRedirect("../index");
		}
		
		
//		세션->id, 저장->
//		인증->저장
//		저장소 -> 세션
//		인증확인 -> 비효율 적인 부분 -> 인증 필터
//		쿠키
		
			
	}
}
