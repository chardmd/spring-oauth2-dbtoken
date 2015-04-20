package com.spring.oauth2.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.oauth2.bean.ErrorMessage;

@ControllerAdvice
public class ErrorController {

	@ExceptionHandler(value = {Exception.class, RuntimeException.class})
	public @ResponseBody ErrorMessage handleException(Exception e, HttpServletResponse response) {
	    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, auth-token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        response.setHeader("Access-Control-Max-Age", "1209600");
	    
	    ErrorMessage errorMessage = new ErrorMessage();
	    errorMessage.setError("400 Bad Request.");
	    String message = e.getMessage();
	    if (message == null) {
	    	message = "The request could not be process by the server due to client error.";
	    }
	    
	    errorMessage.setError_description(message);
	    
	    return errorMessage;
	}
}
