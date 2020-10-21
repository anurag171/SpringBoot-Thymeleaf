package com.anurag.spring.countrydata.domain;

import java.io.Serializable;

public class TokenResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4348991469457536327L;
	

private final String token;

	public TokenResponse(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

}
