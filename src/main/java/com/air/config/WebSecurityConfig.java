package com.air.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.air.security.RestAuthenticationProvider;
import com.air.security.service.filter.JWTAuthenticationFilter;
import com.air.security.service.filter.RestAuthenticationEntryPoint;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	RestAuthenticationProvider restuthenticationProvider;
	
	@Autowired
	JWTAuthenticationFilter jWTAuthenticationFilter;
	
	@Autowired 
	RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	
	 @Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http.csrf().disable()
	    .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
	    .and()
	    .authorizeRequests()
	        .antMatchers("/","/static/*","/login*","/favicon.ico").permitAll()
	        .anyRequest().authenticated()
	        .and()
	        .addFilterBefore(jWTAuthenticationFilter,
	        		BasicAuthenticationFilter.class);
	  }
	 
	
	 
	 @Override
	    protected void configure(AuthenticationManagerBuilder auth) {
	        auth.authenticationProvider(restuthenticationProvider);
	    }

}
