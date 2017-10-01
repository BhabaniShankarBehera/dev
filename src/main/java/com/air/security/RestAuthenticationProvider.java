
package com.air.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.air.entity.User;
import com.air.service.UserService;

/**
 * 
 */

/**
 * @author BhabaniShankar
 *
 */

@Component
public class RestAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	UserService userService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String email = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        
        User user=userService.getUserByEmail(email);
        if(user==null){
        	throw new UsernameNotFoundException("User not found: " + email);
        }
		
		return new UsernamePasswordAuthenticationToken(user, password, Collections.emptyList());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
