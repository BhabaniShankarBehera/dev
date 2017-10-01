/**
 * 
 */
package com.air.repository;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.air.entity.TenantMasterEntity;

/**
 * @author BhabaniShankar
 *
 */
public interface TenantMasterRepository extends JpaRepository<TenantMasterEntity, Serializable>{
	
	Page<TenantMasterEntity> findAllByFirstName(String firstName,Pageable page);

}
