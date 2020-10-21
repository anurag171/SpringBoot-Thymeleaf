package com.anurag.spring.countrydata.repositories;

import java.util.Base64;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Token {
	final private String username;
	final private String token;
	
	public Token(String username,String token) {
		this.username = Base64.getEncoder().encode(username.getBytes()).toString();
		this.token = Base64.getEncoder().encode(token.getBytes()).toString();
	}

	public String getUsername() {
		return Base64.getDecoder().decode(username).toString();
	}
	
	public String getToken() {
		return Base64.getDecoder().decode(token).toString();
	}
}