package com.newlecture.web.controller.api.notice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;

@WebServlet("/api/board/notice/reg")
@MultipartConfig(
    fileSizeThreshold = 1024*1024,
    maxFileSize = 1024*1024*5, //5메가
    maxRequestSize = 1024*1024*5*5 // 5메가 5개까지
)
public class RegController extends HttpServlet{
	
	private NoticeService service;
	
	public RegController() {
		service =  new NoticeService();
	}
	
	@Override
	protected void service(HttpServletRequest request, 
									HttpServletResponse response) throws ServletException, IOException {
				
		if(request.getMethod().equals("GET"))
			request.getRequestDispatcher("reg.jsp").forward(request, response);
		else if(request.getMethod().equals("POST")) {
			
			//0. 사용자 입력을 변수에 담기
			String title = request.getParameter("title");
			//String file = request.getParameter("file");	
			//Part filePart = request.getPart("file");
			String content = request.getParameter("content");
			
			Collection <Part> fileParts = request.getParts();
			
			String fileNames = "";
			
			for(Part p : fileParts) {
				if(p.getName().equals("file") && p.getSize() > 0) {
					
					Part filePart = p;

					String fileName = filePart.getSubmittedFileName();
					fileNames += fileName;
					fileNames += ",";
					
					int newId = service.getLastId() + 1;				
					
					String pathTemp = request.getServletContext().getRealPath("/static/notice/2020/"+newId+"/");
					
					File path = new File(pathTemp);
					if(!path.exists())
						path.mkdirs();
					
					String filePath = pathTemp + File.separator + fileName;		
					System.out.println(filePath);
					
					InputStream fis = filePart.getInputStream();
					FileOutputStream fos = new FileOutputStream(filePath);
					
					byte[] buf = new byte[1024];
					int size = 0;
					while((size = fis.read(buf)) != -1)
						fos.write(buf, 0, size);
					
					fos.close();
					fis.close();
					
				}
			}
			

//
//			Notice notice = new Notice(title, content);
//			
//			if(filePart != null) {
//				String fileName = filePart.getSubmittedFileName();
//				notice.setFiles(fileName);
//						
//				int newId = service.getLastId() + 1;				
//				
//				String pathTemp = request.getServletContext().getRealPath("/static/notice/2020/"+newId+"/");
//								
//				File path = new File(pathTemp);
//				if(!path.exists())
//					path.mkdirs();
//				
//				String filePath = pathTemp + File.separator + fileName;		
//				System.out.println(filePath);
//				
//				InputStream fis = filePart.getInputStream();
//				FileOutputStream fos = new FileOutputStream(filePath);
//				
//				byte[] buf = new byte[1024];
//				int size = 0;
//				while((size = fis.read(buf)) != -1)
//					fos.write(buf, 0, size);
//				
//				fos.close();
//				fis.close();
//			}
//			
//			System.out.printf("title:%s, file:%s\n", title, filePart.getSubmittedFileName());
			
			//1. 데이터베이스에 입력
			NoticeService service = new NoticeService();
			Notice notice = new Notice(title, content);
			// 꼬랑지 떼기~
			notice.setFiles(fileNames); // "img1.jpg,img2.png,"
			notice.setWriterId("newlec");
			service.insert(notice);
			
			//2.목록페이지로 이동
			// Servlet -> Servlet : forward(ing) / redirect(ion)
			//response.sendRedirect("list");
		}
			
		
	}
}
