/**
 * 
 */
package com.air.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
import com.air.entity.FilerBranch;
import com.air.entity.User;
import com.air.service.BranchService;
import com.air.service.FilerService;
import com.air.service.UserService;
import com.air.vo.BranchAddressInfo;
import com.air.vo.BranchContactInfo;
import com.air.vo.BranchLegalInfo;

/**
 * @author BhabaniShankar
 *
 */
@RestController
@RequestMapping("/filer")
public class BranchController {
	private static Logger log = LoggerFactory.getLogger(BranchController.class);
	@Autowired
	MessageSource messages;
	@Autowired
	BranchService branchService;

	@Autowired
	FilerService filerService;

	@Autowired
	UserService userService;

	@Autowired
	HttpSession httpSession;

	@RequestMapping(value = "/{filerid}/save/branch", method = RequestMethod.POST)
	public HttpEntity<CommonResponse> saveBranchLegalInfo(@PathVariable BigInteger filerid,
			@RequestBody @Valid BranchLegalInfo branchLegalInfo, BindingResult result) {
		CommonResponse response = new CommonResponse();
		FilerBranch filerBranch = null;
		log.info("BranchLegalInfo : {} ", branchLegalInfo);
		if (result.hasErrors()) {
			List<String> errormessages = new ArrayList<>();
			for (FieldError e : result.getFieldErrors()) {
				errormessages.add(e.getDefaultMessage());
			}

			response.setError(errormessages);
			return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
		} else {

			filerBranch = new FilerBranch();
			User user = (User) httpSession.getAttribute("airUser");
			user = userService.getUser(user.getId());
			log.info("User Object {}", user);

			filerBranch.setBranchCode(branchLegalInfo.getBranchCode());
			filerBranch.setBranchName(branchLegalInfo.getBranchName());
			filerBranch.setBranchRef(branchLegalInfo.getBranchRef());
			filerBranch.setBranchRefName(branchLegalInfo.getBranchRefName());
			filerBranch.setTan(branchLegalInfo.getPan());
			filerBranch.setPan(branchLegalInfo.getPan());
			filerBranch.setEmail(branchLegalInfo.getEmail());

			filerBranch.setFiler(filerService.getFiler(filerid));
			filerBranch.setUser(user);
			filerBranch = branchService.saveBranch(filerBranch);
			response.setData(filerBranch);
			return new ResponseEntity<>(response, HttpStatus.OK);

		}

	}

	@RequestMapping(value = "/{filerid}/save/branch/address/{branchid}", method = RequestMethod.POST)
	public HttpEntity<CommonResponse> saveBranchAddressInfo(@PathVariable BigInteger filerid,
			@PathVariable BigInteger branchid, @RequestBody @Valid BranchAddressInfo branchAddressInfo,
			BindingResult result) {
		CommonResponse response = new CommonResponse();
		FilerBranch filerBranch = null;
		log.info("BranchAddressInfo : {} ", branchAddressInfo);
		if (result.hasErrors()) {
			List<String> errormessages = new ArrayList<>();
			for (FieldError e : result.getFieldErrors()) {
				errormessages.add(e.getDefaultMessage());
			}

			response.setError(errormessages);
			return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
		} else {

			filerBranch = branchService.getBranch(branchid);
			filerBranch.setPostalCode(branchAddressInfo.getPostalCode());
			filerBranch.setDistrict(branchAddressInfo.getDistrict());
			filerBranch.setCity(branchAddressInfo.getCity());
			filerBranch.setStreetAddress(branchAddressInfo.getAddress());
			filerBranch.setState(branchAddressInfo.getState());
			filerBranch = branchService.saveBranch(filerBranch);
			response.setData(filerBranch);
			return new ResponseEntity<>(response, HttpStatus.OK);

		}

	}

	@RequestMapping(value = "/{filerid}/save/branch/contact/{branchid}", method = RequestMethod.POST)
	public HttpEntity<CommonResponse> saveBranchContactInfo(@PathVariable BigInteger filerid,
			@PathVariable BigInteger branchid, @RequestBody @Valid BranchContactInfo branchContactInfo,
			BindingResult result) {
		CommonResponse response = new CommonResponse();
		FilerBranch filerBranch = null;
		log.info("BranchContactInfo : {}", branchContactInfo);
		if (result.hasErrors()) {
			List<String> errormessages = new ArrayList<>();
			for (FieldError e : result.getFieldErrors()) {
				errormessages.add(e.getDefaultMessage());
			}
			response.setError(errormessages);
			return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
		} else {
			filerBranch = branchService.getBranch(branchid);
			filerBranch.setPhoneNumber(branchContactInfo.getTelephoneNumber());
			filerBranch.setMobileNumber(branchContactInfo.getMobileNumber());
			filerBranch.setFax(branchContactInfo.getFax());

			filerBranch = branchService.saveBranch(filerBranch);
			response.setData(filerBranch);
			return new ResponseEntity<>(response, HttpStatus.OK);

		}

	}

}
