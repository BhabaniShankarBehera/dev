/**
 * 
 */
package com.air.service;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.air.entity.FinancialYearMaster;
import com.air.repository.FinancialYearMasterRepository;

/**
 * @author BhabaniShankar
 *
 */
@Service
public class FinancialYearMasterService {
	@Autowired
	FinancialYearMasterRepository financialYearMasterRepository;
	
	public FinancialYearMaster findById(BigInteger id){
		return financialYearMasterRepository.findById(id);
		
	}
}
