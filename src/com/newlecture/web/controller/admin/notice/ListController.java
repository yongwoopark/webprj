package com.newlecture.web.controller.admin.notice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.newlecture.web.entity.NoticeView;
import com.newlecture.web.service.NoticeService;

@WebServlet("/admin/board/notice/list")
public class ListController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		HttpSession session  = request.getSession();
//		
//		// 인증 했는지 검사
//		if(session.getAttribute("uid") == null) {
//			response.sendRedirect("../../../member/login?return-url=../admin/board/notice/list");
//			return;
//		}
//		// 권한이 있는지 검사
//		if(!session.getAttribute("role").equals("admin")) {
//			response.sendRedirect("../../../error/error?errorNo=403");
//			return;
//		}
		
		// 너 로그인하고 와~
		
		NoticeService service = new NoticeService();
		List<NoticeView> list = service.getViewList(1, 10);
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("list.jsp").forward(request, response);
		
	}
}
