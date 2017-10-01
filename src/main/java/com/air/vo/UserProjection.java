/**
 * 
 */
package com.air.vo;

import java.io.Serializable;
import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author BhabaniShankar
 *
 */
public interface UserProjection extends Serializable {

	BigInteger getId();

	String getEmail();

	String getFirstName();
    
	String getLastName();

	String getMiddleName();

	String getMobile();


	@Value("#{target.filerList.size()}")
	Integer getFilerCount();

	@Value("#{target.filerBranchList.size()}")
	Integer getFilerBranchCount();


}
