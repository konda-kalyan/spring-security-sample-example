package com.example.demo.security;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.microsoft.azure.spring.autoconfigure.aad.AADAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableWebSecurity
public class MySecurity extends WebSecurityConfigurerAdapter {
	
	ArrayList<String> publicURLs = new ArrayList<String>(Arrays.asList(
			"/test",
			"/submit",
			"/swagger*/**"));
	
	@Autowired
	private AADAuthenticationFilter aadAuthFilter;
	
	@Autowired
	private CustomOncePerRequestFilter customOncePerRequestFilter;
	
	
//	private AADAppRoleStatelessAuthenticationFilter aadAuthFilter;
//	@Autowired
//	private CustomAADAuthenticationFilter aadAuthFilter;
	
//	 @Override
//	    public void configure(WebSecurity web) throws Exception {
//	        web.ignoring().antMatchers("/**");
//	    }
	 
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		System.out.println("----------------Kalyan: configure WebSecurity - 11111 -------------------");
//		web
//	      .ignoring()
//	        .antMatchers("/test");
//	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		System.out.println("----------------Kalyan: configure - 11111 -------------------");
		
//		http.cors();
		
		http.authorizeRequests().antMatchers("/test/**").permitAll()
		.anyRequest().authenticated();		
		
//		if(publicURLs.contains(request.getRequestURL().toString())) {
//        	System.out.println("----------------Kalyan: iffff doFilterInternal - 11111 -------------------");
//        	return;
//        }
		http.addFilterBefore(customOncePerRequestFilter, UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(aadAuthFilter, UsernamePasswordAuthenticationFilter.class);
//		http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//		.addFilterBefore(aadAuthFilter, UsernamePasswordAuthenticationFilter.class);
//		
		System.out.println("----------------Kalyan: configure - 22222 -------------------");
//		http.exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint());	
		System.out.println("----------------Kalyan: configure - 3333333 -------------------");
	}
	
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//      // NOTE: A real implementation should have a nonce cache so the token cannot be reused
//
//      CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
//
//      if (
//      // only care if it's a POST
//      "POST".equals(request.getMethod()) &&
//      // ignore if the request path is in our list
//          Arrays.binarySearch(ignoreCsrfAntMatchers, request.getServletPath()) < 0 &&
//          // make sure we have a token
//          token != null) {
//          // CsrfFilter already made sure the token matched. Here, we'll make sure it's not expired
//          try {
//              Jwts.parser()
//                  .setSigningKeyResolver(secretService.getSigningKeyResolver())
//                  .parseClaimsJws(token.getToken());
//          } catch (JwtException e) {
//              // most likely an ExpiredJwtException, but this will handle any
//              request.setAttribute("exception", e);
//              response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//              RequestDispatcher dispatcher = request.getRequestDispatcher("expired-jwt");
//              dispatcher.forward(request, response);
//          }
//      }
//
//      filterChain.doFilter(request, response);
//  }
	
	/*
	 * @Bean CorsConfigurationSource corsConfigurationSource() { CorsConfiguration
	 * configuration = new CorsConfiguration();
	 * configuration.setAllowedOrigins(Arrays.asList("*"));
	 * configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE",
	 * "PATCH", "OPTIONS")); configuration.setAllowedHeaders(Arrays.asList("*")); //
	 * configuration.setExposedHeaders(Arrays.asList("Access-Control-Allow-Origin"))
	 * ; configuration.setAllowCredentials(true);
	 * //configuration.setAllowedHeaders(Arrays.asList("*",
	 * "Access-Control-Allow-Origin"));
	 * 
	 * UrlBasedCorsConfigurationSource source = new
	 * UrlBasedCorsConfigurationSource(); source.registerCorsConfiguration("/**",
	 * configuration); return source; }
	 */
}