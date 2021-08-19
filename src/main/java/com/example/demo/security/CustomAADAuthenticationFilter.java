package com.example.demo.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microsoft.azure.spring.autoconfigure.aad.AADAuthenticationFilter;
import com.microsoft.azure.spring.autoconfigure.aad.AADAuthenticationProperties;
import com.microsoft.azure.spring.autoconfigure.aad.ServiceEndpointsProperties;
import com.nimbusds.jose.util.ResourceRetriever;

public class CustomAADAuthenticationFilter extends AADAuthenticationFilter{

	ArrayList<String> publicURLs = new ArrayList<String>(Arrays.asList(
	"/test",
	"/submit",
	"/swagger*/**"));
	
	public CustomAADAuthenticationFilter(AADAuthenticationProperties aadAuthProps,
			ServiceEndpointsProperties serviceEndpointsProps, ResourceRetriever resourceRetriever) {
		super(aadAuthProps, serviceEndpointsProps, resourceRetriever);
		// TODO Auto-generated constructor stub
	}
	
	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        
        if(publicURLs.contains(request.getRequestURL().toString())) {
        	System.out.println("----------------Kalyan: iffff doFilterInternal - 11111 -------------------");
        	return;
        }
        System.out.println("----------------Kalyan: doFilterInternal - 22222  -------------------");
        super.doFilterInternal(request, response, filterChain);
//		final String authHeader = request.getHeader(TOKEN_HEADER);
//
//        if (!alreadyAuthenticated() && authHeader != null && authHeader.startsWith(TOKEN_TYPE)) {
//            verifyToken(request.getSession(), authHeader.replace(TOKEN_TYPE, ""));
//        }
//
//        filterChain.doFilter(request, response);
    }
}
