package com.spring.oauth2.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;


@Controller
public class TokenController {
	
	@Autowired
	private JdbcTokenStore jdbcTokenStore;
	
	@RequestMapping(value = "/rest/api/revoke_token", method = RequestMethod.POST, produces="application/json; charset=utf-8")
	public @ResponseBody String invalidateToken(HttpServletRequest req, HttpServletResponse res) {
	
			//get the current authentication
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			OAuth2Authentication oAuth2 = ((OAuth2Authentication) authentication);
			
			//remove access token
			OAuth2AccessToken accessToken = jdbcTokenStore.getAccessToken(oAuth2);
			jdbcTokenStore.removeAccessToken(accessToken);
			
			//remove refresh token
			OAuth2RefreshToken refreshToken = accessToken.getRefreshToken();
			jdbcTokenStore.removeRefreshToken(refreshToken);
			
			JsonObject response = new JsonObject();
			response.addProperty("success", true);
			response.addProperty("access_token", accessToken.getValue());
			response.addProperty("message", "Access token has been revoked.");
			
			String strResponse = response.toString();
			
			return strResponse;
	}
	
}
