package com.air.service;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.air.entity.FilerBranch;
import com.air.repository.BranchRepository;

@Service
public class BranchService {
	@Autowired
	BranchRepository branchRepository;
	
	public FilerBranch saveBranch(FilerBranch filerBranch){
		return branchRepository.save(filerBranch);
		
	}
	public FilerBranch getBranch(BigInteger id){
		return branchRepository.findById(id);
		
	}
	
}

