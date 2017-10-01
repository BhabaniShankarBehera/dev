/**
 * 
 */
package com.air.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.io.File;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.air.common.vo.CommonResponse;
import com.air.entity.InvoiceEntity;
import com.air.entity.TenantMasterEntity;
import com.air.entity.User;
import com.air.repository.InvoiceRepository;
import com.air.repository.TenantMasterRepository;
import com.air.service.AuthenticationService;
import com.air.service.TokenAuthenticationService;
import com.air.service.UserService;
import com.air.vo.AddressVo;
import com.air.vo.LoginVo;
import com.air.vo.RegistrationVo;
import com.air.vo.UserVo;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 * @author BhabaniShankar
 *
 */
@RestController
@CrossOrigin
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	// Find your Account Sid and Token at twilio.com/user/account
	//For test use your testAccount Sid and testToken 
	  private static final String ACCOUNT_SID = "AC55b8cdd805aef2584ab72256c066473c";
	  private static final String AUTH_TOKEN = "588de5b7c1fb0999743af41019372004";

	@Autowired
	private JavaMailSender emailSender;

	@Value("${air.app.name}")
	private String fileLoc;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationService authService;
	@Autowired
	private UserResourceAssembler userResourceAssembler;

	@Autowired
	private TokenAuthenticationService tokenService;

	@Autowired
	private TenantMasterRepository tenantMasterRepo;

	@Autowired
	private InvoiceRepository invoiceRepo;

	@Autowired
	private TenantResourceAssembler tenantResourceAssembler;

	private static final String TOKEN_PREFIX = "TOKEN_";
	private static final String HEADER_STRING = "Authorization";

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public HttpEntity<CommonResponse> login(@RequestBody LoginVo loginVo) {

		CommonResponse body = new CommonResponse();
		ResponseEntity<CommonResponse> response = null;
		UserVo userDetails = authService.authenticate(loginVo);
		if (userDetails != null) {
			body.setData(userDetails);
			HttpHeaders header = new HttpHeaders();
			header.add(HEADER_STRING, TOKEN_PREFIX + " " + tokenService.generateToken(userDetails));
			response = new ResponseEntity<>(body, header, HttpStatus.OK);
			return response;
		} else {
			body.setMessage("Invalid UserName !");
			response = new ResponseEntity<>(body, HttpStatus.UNPROCESSABLE_ENTITY);
			return response;
		}

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public HttpEntity<CommonResponse> register(@RequestBody RegistrationVo registrationVo) {

		CommonResponse response = new CommonResponse();
		User user = new User();
		user.setEmail(registrationVo.getEmail());
		user.setFirstName(registrationVo.getFirstName());
		user.setMiddleName(registrationVo.getMiddleName());
		user.setLastName(registrationVo.getLastName());
		user.setMobile(registrationVo.getMobile());
		userService.saveUser(user);
		response.setData(user);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/user", method = RequestMethod.GET)
	public PagedResources<ResourceSupport> searchUser(Pageable pagable, PagedResourcesAssembler<User> assembler,
			Map<String, String> params) {
		Page<User> user = userService.search(params, pagable);
		return assembler.toResource(user, userResourceAssembler,
				linkTo(methodOn(LoginController.class).searchUser(pagable, assembler, params)).withSelfRel());
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public Resource<User> findUserById(@PathVariable BigInteger id) {
		User user = userService.getUser(id);
		Link link = linkTo(methodOn(LoginController.class).findUserById(id)).withSelfRel();
		return new Resource<>(user, link);
	}

	@RequestMapping(value = "/save/address", method = RequestMethod.POST)
	public HttpEntity<CommonResponse> saveAddress(@RequestBody AddressVo addressVo) {
		CommonResponse response = new CommonResponse();
		response.setData(addressVo);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public PagedResources<ResourceSupport> test(Pageable pageable,
			PagedResourcesAssembler<TenantMasterEntity> assembler) {
		Page<TenantMasterEntity> tenant = tenantMasterRepo.findAllByFirstName("Bhabani", pageable);
		return assembler.toResource(tenant, tenantResourceAssembler,
				linkTo(methodOn(LoginController.class).test(pageable, assembler)).withSelfRel());

	}

	@RequestMapping(value = "/getCount", method = RequestMethod.GET)
	public HttpEntity<CommonResponse> getCount() {
		CommonResponse response = new CommonResponse();
		response.setData(invoiceRepo.countByTenantId("dafnzyinwnqvdjytcrks"));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/{searchText}", method = RequestMethod.GET)
	public HttpEntity<CommonResponse> search(@PathVariable String searchText, Pageable pageable) {
		logger.info("search() : {}", searchText);
		CommonResponse response = new CommonResponse();
		Page<InvoiceEntity> page = invoiceRepo.findByInvoiceNumberContainingIgnoreCaseOrTenantEmailContainingIgnoreCase(
				pageable, searchText, searchText);
		response.setData(page);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/sendEmail/{email}", method = RequestMethod.GET)
	public HttpEntity<CommonResponse> sendEmail(@PathVariable String email)
			throws MessagingException {
		HttpEntity<CommonResponse> responseEntity=null;
		CommonResponse response = new CommonResponse();
		logger.info("sendEmail() Email : {}", email);
		if(null!=email && ! "".equals(email)){
			String name = "Bhabani";
			final Context ctx = new Context(Locale.getDefault());
			ctx.setVariable("to", "Hello," + name);
			ctx.setVariable("name", "Bhagini Bar and Restaurant");
			ctx.setVariable("senderName", "Bhabani Shankar Behera");
			ctx.setVariable("subscriptionDate", new Date());
			ctx.setVariable("hobbies", Arrays.asList("Drinking", "Playing", "Music"));
			// ctx.setVariable("imageResourceName", imageResourceName); // so that we can reference it from HTML
			final String htmlContent = templateEngine.process("email-template", ctx);
			final MimeMessage mimeMessage = emailSender.createMimeMessage();
			final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			message.setTo(  email.concat(".com"));
			message.setSubject("Please Come for Drink");
			message.setText(htmlContent, true);
			message.addAttachment("logFile", new File(fileLoc));
			emailSender.send(mimeMessage);
			response.setMessage("Message Sent Sucessfully !");
			responseEntity=new ResponseEntity<>(response, HttpStatus.OK);
			return responseEntity;
		}else{
			response.setMessage("Message Could not Sent Sucessfully !.Incorrect Email");
			responseEntity= new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
			return responseEntity;
		}
		
	}
	
	@RequestMapping(value = "/sendMessage", method = RequestMethod.GET)
	public HttpEntity<CommonResponse> sendMessage()
			 {
		logger.info("sendMessage() .. start---->");
		CommonResponse response = new CommonResponse();
		
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

	    Message message = Message.creator(new PhoneNumber("+919902407433"),
	        new PhoneNumber("+15005550006"), 
	        "This is the ship that made the Kessel Run in fourteen parsecs?").create();
	    
	    logger.info("sendMessage() .. message Id : {}",message.getSid());
		
		response.setMessage("Message Sent Sucessfully !");
		logger.info("sendMessage() .. end---->");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
