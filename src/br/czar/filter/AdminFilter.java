package br.czar.filter;

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

import br.czar.model.Privilege;
import br.czar.model.User;

@WebFilter(filterName = "AdminFilter", urlPatterns = {"/faces/admin/*"})
public class AdminFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest servletRequest = (HttpServletRequest) request;

		HttpSession session = servletRequest.getSession(false);
		
		User user = null;
		if (session != null)
			user = (User)session.getAttribute("userData");
		
		if (user == null) 
			((HttpServletResponse) response).sendRedirect("/LocadoraCzar/faces/login.xhtml");
		
		else if (Privilege.ADMIN.equals(user.getPrivilege())) {
			chain.doFilter(request, response);
			return;
		} else 
			((HttpServletResponse) response).sendRedirect("/LocadoraCzar/faces/login.xhtml");
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
		System.out.println("Admin Filter Funcionando.");
	}

}
