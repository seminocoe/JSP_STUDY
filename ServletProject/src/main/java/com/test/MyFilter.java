package com.test;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = {"/*"})
public class MyFilter implements Filter {

	@Override
	public void destroy() {
		System.out.println("MyFilter 제거");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("MyFilter 요청 필터 코드 작업......");
		//한글 처리 작업
		request.setCharacterEncoding("utf-8");
		
		chain.doFilter(request, response);
		System.out.println("MyFilter요청 필터 코드 작업......");
	}

	@Override
	public void init(FilterConfig fconfig) throws ServletException {
		System.out.println("MyFilter 초기화");
	}

}
