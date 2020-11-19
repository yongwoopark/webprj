import javax.servlet.*;
import javax.servlet.http.*;

import com.newlecture.web.entity.Member;
import com.newlecture.web.service.MemberService;

import java.io.*;
import java.util.List;

public class Nana extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		PrintWriter out = response.getWriter();
		
		

		MemberService service = new MemberService();
		List<Member> list = service.getList();
		
		for(Member m : list)
			//if(m.getPwd().equals("111"))
				out.printf("nicname : %s, name : %s\n", m.getNicname(), m.getName());
		
	}
}
