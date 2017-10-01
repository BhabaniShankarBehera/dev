/**
 * 
 */
package com.air.vo;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author BhabaniShankar
 *
 */
public class BranchContactInfo {

	@NotEmpty
	private String telephoneNumber;

	@NotEmpty
	private String mobileNumber;

	@NotEmpty
	private String fax;

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

}
