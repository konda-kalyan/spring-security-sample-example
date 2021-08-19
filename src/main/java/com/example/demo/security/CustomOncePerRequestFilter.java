package com.example.demo.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class CustomOncePerRequestFilter extends OncePerRequestFilter {
	
	ArrayList<String> publicURLs = new ArrayList<String>(Arrays.asList(
			"/test",
			"/submit",
			"/swagger*/**"));
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("Kalyan: doFilterInternal 111 request.getRequestURL().toString(): -----------" + request.getRequestURL().toString());
		System.out.println("Kalyan: doFilterInternal 111 request.getRequestURL().toString(): -----------" + request.getRequestURI().toString());
//		request.getRequestURL().toString() = "http://localhost:9090/test"
//		request.getRequestURI().toString() = "/test"

		if(publicURLs.contains(request.getRequestURI().toString())) {
        	System.out.println("----------------Kalyan: iffff doFilterInternal - 11111 -------------------");
        	return;
        }

		System.out.println("----------------Kalyan: doFilterInternal - 22222 -------------------");
		filterChain.doFilter(request, response);
	}
}

