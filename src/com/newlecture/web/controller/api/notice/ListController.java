package com.newlecture.web.controller.api.notice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.newlecture.web.entity.NoticeView;
import com.newlecture.web.service.NoticeService;

@WebServlet("/api/board/notice/list")
public class ListController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json; charset=UTF-8");
		
		request.getSession().setAttribute("aa", 12);
		
		int page = 1;
		String page_ = request.getParameter("p");
		
		if(page_ != null && !page_.equals(""))
			page = Integer.parseInt(page_);
		
		NoticeService service = new NoticeService();
		List<NoticeView> list = service.getViewList(page, 10);
		System.out.println(list.size());
		
//		String json = "[";
//		
//		for(int i=0; i<list.size(); i++) {
//			NoticeView n = list.get(i);
//			
//			json += String.format("{\"id\":\"%s\",\"title\":\"%s\"}", n.getId(), n.getTitle());
//			
//			if(list.size() > i+1)
//				json += ",";
//		}
//		
//		json += "]";
		
		String json = new Gson().toJson(list);
		
		response.getWriter().println(json);
		
	}
}
