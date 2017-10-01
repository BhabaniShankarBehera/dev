/**
 * 
 */
package com.air.repository;

import java.io.Serializable;
import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;

import com.air.entity.FinancialYearMaster;

/**
 * @author BhabaniShankar
 *
 */
public interface FinancialYearMasterRepository extends CrudRepository<FinancialYearMaster, Serializable>{

	FinancialYearMaster findById(BigInteger id);
}
