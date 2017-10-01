/**
 * 
 */
package com.air.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author BhabaniShankar
 *
 */
@Entity
public class FinancialYearMaster implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8325720741846721397L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private BigInteger id;
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}

}
