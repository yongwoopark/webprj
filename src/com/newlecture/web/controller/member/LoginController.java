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
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uid = request.getParameter("uid");
		String pwd =request.getParameter("pwd");
		System.out.printf("uid:%s, pwd:%s\n", uid, pwd);
		
		HttpSession session = request.getSession();
		
		if(service.isValid(uid, pwd)) {
			// 유효 하니까... 유효함을 어딘가에 저장해야 한다.
			session.setAttribute("uid", uid);
			response.sendRedirect("../index");
		}
		
		
//		세션->id, 저장->
//		인증->저장
//		저장소 -> 세션
//		인증확인 -> 비효율 적인 부분 -> 인증 필터
//		쿠키
		
			
	}
}
