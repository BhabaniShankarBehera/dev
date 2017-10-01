/**
 * 
 */
package com.air.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.air.entity.TenantMasterEntity;

/**
 * @author BhabaniShankar
 *
 */
@Component
public class TenantResourceAssembler extends ResourceAssemblerSupport<TenantMasterEntity, ResourceSupport> {

	public TenantResourceAssembler() {
		super(LoginController.class, ResourceSupport.class);
	}

	@Override
	public Resource<TenantMasterEntity> toResource(TenantMasterEntity tenant) {
		List<Link> link = new ArrayList<>();

		return new Resource<>(tenant, link);
	}

	public List<Resource<TenantMasterEntity>> toResource(List<TenantMasterEntity> tenant) {
		List<Resource<TenantMasterEntity>> list = new ArrayList<>();
		for (TenantMasterEntity t : tenant) {
			list.add(toResource(t));
		}
		return list;

	}

}
