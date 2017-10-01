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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * @author BhabaniShankar
 *
 */
@Entity(name="invoice")
public class InvoiceEntity  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5564606188522025964L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private BigInteger id;
	
	private String invoiceNumber;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="tenantId")
	private TenantMasterEntity tenant;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public TenantMasterEntity getTenant() {
		return tenant;
	}

	public void setTenant(TenantMasterEntity tenant) {
		this.tenant = tenant;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	

}
