package com.anurag.spring.countrydata.domain;

import java.io.Serializable;

public class TokenRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1376506053560572803L;

	public TokenRequest(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
		}

	private String username;
	
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
