/**
 * 
 */
package com.air.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author BhabaniShankar
 *
 */
@Entity(name = "tenantmaster")
public class TenantMasterEntity implements Serializable {

	private static final long serialVersionUID = 4971721867074185508L;
	@Id
	@GenericGenerator(name = "tenantIdGenerator", strategy = "com.air.entity.TenantIdGenerator")
	@GeneratedValue(generator = "tenantIdGenerator")
	private String id;

	private String email;

	private String firstName;

	private String lastName;

	private String middleName;

	private String mobile;

	@JsonManagedReference
	@OneToMany(mappedBy = "tenant")
	private List<InvoiceEntity> invoice;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setInvoice(List<InvoiceEntity> invoice) {
		this.invoice = invoice;
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

}
