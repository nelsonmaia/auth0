package com.nelsonmatias.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
/**
 * Response from Auth0 that contains a JWT Token encrypted 
 * @author Nelson.Matias
 *
 */
public class Auth0Response {

	private String accessToken;
	private String tokenType;
	
	public String getAccessToken() {
		return accessToken;
	}
	
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public String getTokenType() {
		return tokenType;
	}
	
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	@Override
	public String toString() {
		return this.accessToken;
	}
}
