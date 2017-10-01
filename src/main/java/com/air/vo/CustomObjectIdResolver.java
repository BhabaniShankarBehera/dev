/**
 * 
 */
package com.air.vo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.ObjectIdGenerator.IdKey;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.annotation.SimpleObjectIdResolver;

/**
 * @author BhabaniShankar
 *
 */
@Component
public class CustomObjectIdResolver implements ObjectIdResolver {

	@Autowired
	private ObjectIdResolver objectIdResolver;

	@Override
	public void bindItem(IdKey id, Object pojo) {
		// Time to drop the references?
		if (pojo instanceof CountryVo)
			clearReferences();

		objectIdResolver.bindItem(id, pojo);
	}

	@Override
	public Object resolveId(IdKey id) {
		return objectIdResolver.resolveId(id);
	}

	@Override
	public ObjectIdResolver newForDeserialization(Object context) {
		return new CustomObjectIdResolver();
	}

	@Override
	public boolean canUseFor(ObjectIdResolver resolverType) {
		return resolverType.getClass() == getClass();
	}

	private void clearReferences() {
		objectIdResolver = new SimpleObjectIdResolver();
	}

}
