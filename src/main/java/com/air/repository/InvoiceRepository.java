/**
 * 
 */
package com.air.repository;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.air.entity.InvoiceEntity;

/**
 * @author BhabaniShankar
 *
 */
public interface InvoiceRepository extends CrudRepository<InvoiceEntity, Serializable> {

	Long countByTenantId(String tenantId);

	Page<InvoiceEntity> findByInvoiceNumberContainingIgnoreCaseOrTenantEmailContainingIgnoreCase(Pageable page,
			String invoiceNumber, String email);

}
