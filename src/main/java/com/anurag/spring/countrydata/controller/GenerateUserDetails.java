package com.anurag.spring.countrydata.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class GenerateUserDetails {
	
	
	
	private static List<GrantedAuthority> buildUserAuthority(Set<String> userRoles) {
	    Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>(); 
	    for(String userRole  : userRoles){
	        System.out.println("called buildUserAuthority(Set<UserRole> userRoles) method.....");
	        setAuths.add(new SimpleGrantedAuthority(userRole));
	    }

	    List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>(setAuths);
	    return grantedAuthorities;
	}

	private static User buildUserForAuthentication(com.anurag.spring.countrydata.domain.User user, List<GrantedAuthority> authorities) {
	    //accountNonExpired, credentialsNonExpired, accountNonLocked, authorities properties
	    System.out.println("called buildUserForAuthentication(Users user, List<GrantedAuthority> authorities) method....");
	    return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
	}

	public static UserDetails getUserDetails(com.anurag.spring.countrydata.domain.@Valid User user) {
		
		Set<String> roleSet = new HashSet<String>();
		roleSet.add("ADMIN");
		
		return buildUserForAuthentication(user,buildUserAuthority(roleSet));
	}
	
}

