/**
 * 
 */
package com.air.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.air.common.vo.CommonResponse;

/**
 * @author BhabaniShankar
 *
 */
@RestControllerAdvice
public class GlobalControllerExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ExceptionHandler(value = { Exception.class })
    public HttpEntity<CommonResponse> unknownException(Exception ex, WebRequest req) {
    	HttpEntity<CommonResponse> responseEntity=null;
		CommonResponse response = new CommonResponse();
		 logger.error("Exception : {}",ex);
		response.setMessage("An Internal Server Error Occured .Please try after some time. !");
		responseEntity= new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
		return responseEntity;
    }
}