package com.spring.oauth2.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;

@Controller
public class YourCustomController {
	
	@RequestMapping(value = "/rest/api/customapi", method = RequestMethod.POST, produces="application/json; charset=utf-8")
	public @ResponseBody String invalidateToken(HttpServletRequest req, HttpServletResponse res) {
			
			JsonObject response = new JsonObject();
			response.addProperty("success", true);
			response.addProperty("message", "Return what ever you like. This API is protected by access token.");
			
			String strResponse = response.toString();
			
			return strResponse;
	}
	
}
