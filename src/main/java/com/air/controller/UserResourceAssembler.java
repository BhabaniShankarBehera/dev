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

import com.air.entity.User;

/**
 * @author BhabaniShankar
 *
 */
@Component

public class UserResourceAssembler extends ResourceAssemblerSupport<User, ResourceSupport> {

	public UserResourceAssembler() {
		super(LoginController.class, ResourceSupport.class);
	}

	@Override
	public Resource<User> toResource(User user) {
		List<Link> link = new ArrayList<>();

		return new Resource<>(user, link);
	}

	public List<Resource<User>> toResource(List<User> userList) {
		List<Resource<User>> list = new ArrayList<>();
		for (User user : userList) {
			list.add(toResource(user));
		}
		return list;

	}

}
