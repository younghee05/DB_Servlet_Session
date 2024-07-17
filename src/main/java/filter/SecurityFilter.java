package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import entity.User;

//@WebFilter({"/mypage", "/mypage/password"}) // 객체 x 배열형태로 들어옴 
@WebFilter(filterName = "securityFilter")
public class SecurityFilter extends HttpFilter implements Filter {
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request; // 다운 캐스팅
		HttpSession session = httpServletRequest.getSession();
		
		
		User user = (User) session.getAttribute("authentication");
		if(user == null) {
			StringBuilder responseBody = new StringBuilder();
			responseBody.append("<script>");
			responseBody.append("alert('잘못된 접근입니다. \\n로그인 후에 이용바랍니다.');"); // \\ : "\" 하나로 적용된다 
			responseBody.append("location.href='/ssa/login';");
			responseBody.append("</script>");
			response.setContentType("text/html");
			response.getWriter().println(responseBody.toString());
			return; 
		}
		
		chain.doFilter(request, response);
	}

	
}
