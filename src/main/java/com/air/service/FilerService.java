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

import com.air.entity.Filer;
import com.air.repository.FilerRepository;

/**
 * @author BhabaniShankar
 *
 */
@Service
public class FilerService {
	@Autowired
	FilerRepository filerRepository;
	
	public Filer saveFiler(Filer filer){
		return filerRepository.save(filer);
		
	}
	public Filer getFiler(BigInteger filerid){
		return filerRepository.findById(filerid);
		
	}
	public Page<Filer> search(Map<String, String> params,Pageable pageable){
		
		
		return filerRepository.search(pageable, params);
		
	}
}
