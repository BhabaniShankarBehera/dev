/**
 * 
 */
package com.air.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author BhabaniShankar
 *
 */
@Entity
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8028952214204732124L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id;
	
	private Boolean active;
	

	private String email;

	private String firstName;
	
	

	private String lastName;

	private String middleName;

	private String mobile;

	
	@JsonIgnore
	private String usercol;

	@JsonBackReference
	@OneToMany(mappedBy = "user")
	private List<Filer> filerList;

	@JsonBackReference
	@OneToMany(mappedBy = "user")
	private List<FilerBranch> filerBranchList;

	

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}



	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	

	public String getUsercol() {
		return usercol;
	}

	public void setUsercol(String usercol) {
		this.usercol = usercol;
	}

	public List<Filer> getFilerList() {
		return filerList;
	}

	public void setFilerList(List<Filer> filerList) {
		this.filerList = filerList;
	}

	public List<FilerBranch> getFilerBranchList() {
		return filerBranchList;
	}

	public void setFilerBranchList(List<FilerBranch> filerBranchList) {
		this.filerBranchList = filerBranchList;
	}

	

}
