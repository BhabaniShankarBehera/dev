/**
 * 
 */
package com.air.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.air.entity.User;
import com.air.security.RestAuthenticationProvider;
import com.air.vo.LoginVo;
import com.air.vo.UserVo;

/**
 * @author BhabaniShankar
 *
 */
@Service
public class AuthenticationService {
	@Autowired
	RestAuthenticationProvider restuthenticationProvider;

	@Autowired
	UserService userService;

	public UserVo authenticate(LoginVo loginVo) {
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginVo.getEmail(),
				loginVo.getPassword());

		Authentication auth = restuthenticationProvider.authenticate(authentication);
		if (auth == null) {
			return null;
		} else {
			User user = userService.getUserByEmail(loginVo.getEmail());
			return new UserVo(user.getEmail(), user.getFirstName(), user.getLastName(), user.getMiddleName(),
					user.getMobile());
		}

	}

}
