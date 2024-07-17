package servlet.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.invalidate(); // 강제로 세션 만료
//		resp.sendRedirect("/ssa/index");
		StringBuilder responseBody = new StringBuilder();
		responseBody.append("<script>");
		responseBody.append("alert('로그아웃 완료 !');");
		responseBody.append("location.href='/ssa/index';");
		responseBody.append("</script>");
		resp.setContentType("text/html");
		resp.getWriter().println(responseBody.toString());
	}

}
