package in.co.rays.proj4.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.co.rays.proj4.util.ServletUtility;

@WebFilter(filterName = "FrontCtl", urlPatterns = { "/ctl/*", "/doc/*" })
public class FrontCtl implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("in do Filter method of front ctl");

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(150);

		if (session.getAttribute("user") == null) {
			request.setAttribute("ErrorMsg", "Your session has been expired please re login ....");
			ServletUtility.forward("LoginView.jsp", request, response);
			return;
		} else {
			chain.doFilter(request, response); // call next config filter/ctl in the chain
		}

	}

	@Override
	public void destroy() {

	}

}