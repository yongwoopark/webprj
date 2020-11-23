import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class AddController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
						
		int x=0;
		int y=0;
		
		x = Integer.parseInt(req.getParameter("x"));
		y = Integer.parseInt(req.getParameter("y"));
		
		int result = x + y;
		
		resp.getWriter().println(result);
	}
	
}
