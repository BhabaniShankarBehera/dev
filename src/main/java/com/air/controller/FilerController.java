/**
 * 
 */
package com.air.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.air.common.vo.CommonResponse;
import com.air.entity.Filer;
import com.air.entity.User;
import com.air.service.FilerService;
import com.air.service.UserService;

/**
 * @author BhabaniShankar
 *
 */

@RestController
public class FilerController {
	private static Logger log = LoggerFactory.getLogger(FilerController.class);
	@Autowired
	FilerService filerService;

	@Autowired
	UserService userService;

	@Autowired
	HttpSession httpSession;

	@Autowired
	FilerResourceAssembler filerResourceAssembler;

	@RequestMapping(value = "/filers", method = RequestMethod.GET)
	public PagedResources<ResourceSupport> search(Pageable pageable, PagedResourcesAssembler<Filer> assembler,
			Map<String, String> params) {
		Page<Filer> filerPage = filerService.search(params, pageable);
		Link link = linkTo(methodOn(FilerController.class).search(pageable, assembler, params)).withRel("filers");
		return assembler.toResource(filerPage, filerResourceAssembler, link);
	}

	@RequestMapping(value = "/filer/save", method = RequestMethod.POST)
	public HttpEntity<CommonResponse> saveFiler(@Valid @RequestBody Filer filer, BindingResult result) {
		CommonResponse response = new CommonResponse();
		if (result.hasErrors()) {
			List<String> errormessages = new ArrayList<>();
			for (FieldError e : result.getFieldErrors()) {
				errormessages.add(e.getDefaultMessage());
			}

			response.setError(errormessages);
			return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
		} else {

			User user = (User) httpSession.getAttribute("airUser");
			user = userService.getUser(user.getId());
			log.info("User Object {}", user);

			Filer filerOutput = filerService.saveFiler(filer);
			response.setData(filerOutput);
			return new ResponseEntity<>(response, HttpStatus.OK);

		}

	}

	@RequestMapping(value = "/filer/{filerid}", method = RequestMethod.POST)
	public HttpEntity<CommonResponse> showFilerDetails(@PathVariable BigInteger filerid) {
		CommonResponse response = new CommonResponse();

		User user = (User) httpSession.getAttribute("airUser");
		user = userService.getUser(user.getId());
		log.info("User Object  {}", user);

		Filer filer = filerService.getFiler(filerid);
		response.setData(filer);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}
