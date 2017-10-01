package com.air.security.service.filter;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.air.service.UserService;
import com.air.vo.LoginVo;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter implements Filter {

	

	@Autowired 
	AuthenticationManager authManager;
	
	@Autowired 
	UserService userService;
	
	@Autowired
	AuthenticationSuccessHandler successHandler;
	
	@Autowired
    AuthenticationFailureHandler failureHandler;
	
	
	 public JWTLoginFilter(String url, AuthenticationManager authManager) {
		    super(new AntPathRequestMatcher(url));
		    this.authManager=authManager;
		  }

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		LoginVo loginDetails=new ObjectMapper()
		        .readValue(request.getInputStream(), LoginVo.class);
		
		UsernamePasswordAuthenticationToken token= new UsernamePasswordAuthenticationToken(
	        		loginDetails.getEmail(),
	        		loginDetails.getPassword(),
	            Collections.emptyList()
	          );
		return this.authManager.authenticate(token);
		
	}

	@Override
	  protected void successfulAuthentication(
	      HttpServletRequest req,
	      HttpServletResponse res, FilterChain chain,
	      Authentication auth) throws IOException, ServletException {
		chain.doFilter(req, res);
	 
	  }
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException failed)
			throws IOException, ServletException {
		// Do nothing 

		
	}
}
