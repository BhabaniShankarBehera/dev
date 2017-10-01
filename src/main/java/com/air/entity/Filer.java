/**
 * 
 */
package com.air.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author BhabaniShankar
 *
 */
@Entity
public class Filer implements Serializable{

	private static final long serialVersionUID = -3308565562218376127L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private BigInteger id;
	
	private int active;

	private String code;

	private Timestamp createdTs;

	private Timestamp deactivatedTs;

	private Timestamp deactivateTs;

	private String email;

	private String fax;

	private Timestamp modifiedTs;

	private String name;

	private String pan;

	private Boolean panValidated;

	private Timestamp panValidatedTs;

	private String phone;

	private String registrationNumber;

	private int statusId;
	
	private BigInteger createdBy;
	private BigInteger modifiedBy;
	private BigInteger deactivatedBy;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="createdBy",insertable=false,updatable=false)
	private User user;

	@JsonManagedReference
	@OneToMany(mappedBy="filer")
	private List<FilerBranch> filerBranch;

	

	



	public BigInteger getId() {
		return id;
	}



	public void setId(BigInteger id) {
		this.id = id;
	}



	public int getActive() {
		return active;
	}



	public void setActive(int active) {
		this.active = active;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public Timestamp getCreatedTs() {
		return createdTs;
	}



	public void setCreatedTs(Timestamp createdTs) {
		this.createdTs = createdTs;
	}



	public Timestamp getDeactivatedTs() {
		return deactivatedTs;
	}



	public void setDeactivatedTs(Timestamp deactivatedTs) {
		this.deactivatedTs = deactivatedTs;
	}



	public Timestamp getDeactivateTs() {
		return deactivateTs;
	}



	public void setDeactivateTs(Timestamp deactivateTs) {
		this.deactivateTs = deactivateTs;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getFax() {
		return fax;
	}



	public void setFax(String fax) {
		this.fax = fax;
	}



	public Timestamp getModifiedTs() {
		return modifiedTs;
	}



	public void setModifiedTs(Timestamp modifiedTs) {
		this.modifiedTs = modifiedTs;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getPan() {
		return pan;
	}



	public void setPan(String pan) {
		this.pan = pan;
	}



	public Boolean getPanValidated() {
		return panValidated;
	}



	public void setPanValidated(Boolean panValidated) {
		this.panValidated = panValidated;
	}



	public Timestamp getPanValidatedTs() {
		return panValidatedTs;
	}



	public void setPanValidatedTs(Timestamp panValidatedTs) {
		this.panValidatedTs = panValidatedTs;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getRegistrationNumber() {
		return registrationNumber;
	}



	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}



	public int getStatusId() {
		return statusId;
	}



	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public List<FilerBranch> getFilerBranch() {
		return filerBranch;
	}



	public void setFilerBranch(List<FilerBranch> filerBranch) {
		this.filerBranch = filerBranch;
	}



	public BigInteger getCreatedBy() {
		return createdBy;
	}



	public void setCreatedBy(BigInteger createdBy) {
		this.createdBy = createdBy;
	}



	public BigInteger getModifiedBy() {
		return modifiedBy;
	}



	public void setModifiedBy(BigInteger modifiedBy) {
		this.modifiedBy = modifiedBy;
	}



	public BigInteger getDeactivatedBy() {
		return deactivatedBy;
	}



	public void setDeactivatedBy(BigInteger deactivatedBy) {
		this.deactivatedBy = deactivatedBy;
	}

}
