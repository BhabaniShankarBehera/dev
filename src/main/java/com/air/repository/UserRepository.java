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

import com.air.entity.QUser;
import com.air.entity.User;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

/**
 * @author BhabaniShankar
 *
 */
@Relation(collectionRelation = "user", value = "user")
public interface UserRepository extends CrudRepository<User, Serializable>,
		PagingAndSortingRepository<User, Serializable>, QueryDslPredicateExecutor<User> {
	User findById(BigInteger usrid);

	User findByEmail(String emailid);

	public default Page<User> search(Pageable page, Map<String, String> params) {
		Page<User> pagedata = null;
		BooleanBuilder where = new BooleanBuilder();
		QUser user = QUser.user;
		for (Entry<String, String> filter : params.entrySet()) {
			if ("email".equalsIgnoreCase(filter.getKey())) {
				where.and((Predicate) user.email.eq(filter.getValue()));
			}

		}
		pagedata = findAll(where, page);
		return pagedata;
	}

}
