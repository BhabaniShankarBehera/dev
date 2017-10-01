/**
 * 
 */
package com.air.repository;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.hateoas.core.Relation;

import com.air.entity.Filer;
import com.air.entity.QFiler;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

/**
 * @author BhabaniShankar
 *
 */
@Relation(collectionRelation = "filer", value = "filer")
public interface FilerRepository extends CrudRepository<Filer, Serializable>,
PagingAndSortingRepository<Filer, Serializable>
,QueryDslPredicateExecutor<Filer> {

	Filer findById(BigInteger filerid);
	public default Page<Filer> search(Pageable page, Map<String, String> params) {
		Page<Filer> pagedata = null;
		BooleanBuilder where = new BooleanBuilder();
		QFiler filer = QFiler.filer;
		for (Entry<String, String> filter : params.entrySet()) {
			if ("email".equalsIgnoreCase(filter.getKey())) {
				where.and((Predicate) filer.user.email.eq(filter.getValue()));
			}
			

		}
		pagedata = findAll(where,page);
		return pagedata;
	}
}
