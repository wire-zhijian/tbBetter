package com.zhijian.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		String userId = String.valueOf(req.getSession().getAttribute("userId"));
		if(userId == null || userId == "null"){
			res.sendRedirect(req.getContextPath() + "/login.html");
		}else{
			chain.doFilter(req, res);
		}
	}


	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
