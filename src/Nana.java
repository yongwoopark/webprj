import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.newlecture.web.entity.Member;
import com.newlecture.web.service.MemberService;

import java.io.*;
import java.util.List;
//    /notice/list
@WebServlet("/na")
public class Nana extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		PrintWriter out = response.getWriter();

		MemberService service = new MemberService();
		List<Member> list = service.getList();
		
		String html = "<table border='1'>";
		
		for(Member m : list) {
			html += "<tr>";
			html += "<td>" + m.getNicname() + "</td><td>"+m.getName()+"</td>";
			html += "</tr>";
		}
		
		
		html += "</table>";
		
		out.println(html);
			
				
		
	}
}
