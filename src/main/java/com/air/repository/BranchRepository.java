/**
 * 
 */
package com.air.repository;

import java.io.Serializable;
import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;

import com.air.entity.FilerBranch;

/**
 * @author BhabaniShankar
 *
 */
public interface BranchRepository extends CrudRepository<FilerBranch, Serializable>{

	FilerBranch findById(BigInteger id);

}
