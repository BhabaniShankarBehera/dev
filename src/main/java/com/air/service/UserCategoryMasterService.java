/**
 * 
 */
package com.air.service;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.air.entity.UserCategoryMaster;
import com.air.repository.UserCategoryMasterRepository;

/**
 * @author BhabaniShankar
 *
 */
@Service
public class UserCategoryMasterService {
	
	@Autowired 
	UserCategoryMasterRepository userCategoryMasterRepository;
	
	public UserCategoryMaster findById(BigInteger id){
		return userCategoryMasterRepository.findById(id);
		
	}

}
