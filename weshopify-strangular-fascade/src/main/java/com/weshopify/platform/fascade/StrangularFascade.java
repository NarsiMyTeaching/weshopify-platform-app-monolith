package com.weshopify.platform.fascade;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class StrangularFascade implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		System.out.println("Path Information is:\t"+req.getPathInfo());
		System.out.println("Servlet Path:\t"+req.getServletPath());
		System.out.println("Request URI is:\t"+req.getRequestURI());
		System.out.println("context path is:\t"+req.getContextPath());
		if(req.getServletPath().equals("/view-customerBoard")) {
			//call customers microservice
			try {
				String customerListJsonData = CustomerServiceClient.getAllCustomers();
				HttpSession session = req.getSession();
				session.setAttribute("currentPage", 1);
				session.setAttribute("customerData", customerListJsonData);
				session.setAttribute("sortBy", "email");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(req.getServletPath().equals("/save-customer")) {
			//invoke a call to customer-microservice to save the data
			
		}
		chain.doFilter(req, response);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
