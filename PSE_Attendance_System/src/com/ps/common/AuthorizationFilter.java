package com.ps.common;

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

@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.htm","*.jsp"}) 
public class AuthorizationFilter implements Filter {

	public AuthorizationFilter() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {
			HttpServletRequest reqt = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			HttpSession ses = reqt.getSession(false);

			String reqURI = reqt.getRequestURI();
			System.out.println(">>>>>>>>>>>>>"+reqURI);
			// ************************ If User Is Already Login Redirect To
			// Home Page ************************ //
			/*if (ses.getAttribute("employee") == null)
			{
				resp.sendRedirect(reqt.getContextPath() + "/index.jsp");
			}
			else*/ if (reqURI.indexOf("/index.jsp") >= 0 && (ses != null && ses.getAttribute("employee") != null))
			{
				resp.sendRedirect(reqt.getContextPath() + "/home.htm");
			}
			else if (reqURI.indexOf("/index.jsp") >= 0
					|| (ses != null && ses.getAttribute("employee") != null))
			{
				chain.doFilter(request, response);
			}
			else if(reqURI.indexOf("/register-user.htm") > 0 || reqURI.indexOf("/registerEmployee.htm") > 0)
			{
				chain.doFilter(request, response);
			}
			else
			{
				resp.sendRedirect(reqt.getContextPath() + "/index.jsp");
			}
			
		} catch (Exception e) {
			/*System.out.println(e.getMessage());*/
		}
	}

	@Override
	public void destroy() {

	}
}
