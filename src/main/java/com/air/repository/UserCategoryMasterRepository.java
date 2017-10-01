/**
 * 
 */
package com.air.repository;

import java.io.Serializable;
import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;

import com.air.entity.UserCategoryMaster;

/**
 * @author BhabaniShankar
 *
 */
public interface UserCategoryMasterRepository  extends CrudRepository<UserCategoryMaster, Serializable>{

	UserCategoryMaster findById(BigInteger id);
}
