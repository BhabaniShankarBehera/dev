/**
 * 
 */
package com.air.vo;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author BhabaniShankar
 *
 */
public class BranchLegalInfo {
	
	@NotEmpty
	private String branchCode;
	
	@NotEmpty
	private String branchName;
	
	@NotEmpty
	private String branchRef;
    
	@NotEmpty
	private String branchRefName;
	
	@NotEmpty
	@Pattern(regexp = "|[A-Z]{4}[0-9]{5}[A-Z]{1}")
	private String tan;
	
	@NotEmpty
	@Pattern(regexp = "|[A-Z]{5}[0-9]{4}[A-Z]{1}")
	private String pan;

	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	private String email;

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchRef() {
		return branchRef;
	}

	public void setBranchRef(String branchRef) {
		this.branchRef = branchRef;
	}

	public String getBranchRefName() {
		return branchRefName;
	}

	public void setBranchRefName(String branchRefName) {
		this.branchRefName = branchRefName;
	}

	public String getTan() {
		return tan;
	}

	public void setTan(String tan) {
		this.tan = tan;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
