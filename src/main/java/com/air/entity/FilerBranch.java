/**
 * 
 */
package com.air.entity;

/**
 * @author BhabaniShankar
 *
 */

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class FilerBranch implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5941954740260125128L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private BigInteger id;
	
	
	
	
	private String branchCode;
	
	private String branchName;
	
	private String branchRef;
	
	private String branchRefName;
	
	private String pan;
	
	 private String tan;
	
	private String streetAddress;
	
	private String state;
	
	private String postalCode;
	
	private String city;
	
	private String district;
	
	private String email;
	
	private String fax;
	
	private String mobileNumber;
	
	private String phoneNumber;
	
	private Timestamp createdTs;

	private Timestamp deactivatedTs;
	
	private Timestamp lastModifiedTs;
	
	private String remarks;
	
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="filerId")
	private Filer filer;
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="createdBy")
	private User user;


	

	public BigInteger getId() {
		return this.id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}
	
	
	

	public String getBranchCode() {
		return this.branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchName() {
		return this.branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchRef() {
		return this.branchRef;
	}

	public void setBranchRef(String branchRef) {
		this.branchRef = branchRef;
	}

	public String getBranchRefName() {
		return this.branchRefName;
	}

	public void setBranchRefName(String branchRefName) {
		this.branchRefName = branchRefName;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Timestamp getCreatedTs() {
		return this.createdTs;
	}

	public void setCreatedTs(Timestamp createdTs) {
		this.createdTs = createdTs;
	}

	public Timestamp getDeactivatedTs() {
		return this.deactivatedTs;
	}

	public void setDeactivatedTs(Timestamp deactivatedTs) {
		this.deactivatedTs = deactivatedTs;
	}

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Timestamp getLastModifiedTs() {
		return this.lastModifiedTs;
	}

	public void setLastModifiedTs(Timestamp lastModifiedTs) {
		this.lastModifiedTs = lastModifiedTs;
	}

	public String getMobileNumber() {
		return this.mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPan() {
		return this.pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreetAddress() {
		return this.streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getTan() {
		return this.tan;
	}

	public void setTan(String tan) {
		this.tan = tan;
	}

	public Filer getFiler() {
		return this.filer;
	}

	public void setFiler(Filer filer) {
		this.filer = filer;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	

	
}