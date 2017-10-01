/**
 * 
 */
package com.air.service;

import java.math.BigInteger;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.air.entity.User;
import com.air.repository.UserRepository;

/**
 * @author BhabaniShankar
 *
 */
@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	
	public User saveUser(User user){
		return userRepository.save(user);
		
	}
	public User getUser(BigInteger id){
		return userRepository.findById(id);
		
	}
	public User getUserByEmail(String emailid){
		return userRepository.findByEmail(emailid);
		
	}
	public Page<User> search(Map<String, String> params,Pageable pageable){
	

		
		return userRepository.search(pageable, params);
		
	}

}
