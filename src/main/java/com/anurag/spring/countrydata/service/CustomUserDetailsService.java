package com.anurag.spring.countrydata.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.anurag.spring.countrydata.domain.Role;
import com.anurag.spring.countrydata.domain.User;
import com.anurag.spring.countrydata.repositories.RoleRepository;
import com.anurag.spring.countrydata.repositories.Token;
import com.anurag.spring.countrydata.repositories.TokenRepo;
import com.anurag.spring.countrydata.repositories.UserRepo;
import com.anurag.spring.countrydata.security.config.JwtTokenUtil;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepo userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private TokenRepo tokenRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	
	@Autowired
	JwtTokenUtil _jwtUtil;
	
	@Value("${authenticationUrl}")
	private String AUTHENTICATION_URL;
	
	
	
	public User findUserByUsername(String username) {
	    return userRepository.findByUsername(username);
	}
	
	public void saveUser(User user) {
	    user.setPassword(passwordEncoder.encode(user.getPassword()));
	    user.setEnabled(true);
	    Role userRole = roleRepository.findByRole("ADMIN");
	    user.setRoles(new HashSet<>(Arrays.asList(userRole)));
	    userRepository.save(user);
	    Token token = new Token(user.getUsername(), user.getToken());
	    tokenRepository.save(token);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);		
		//TokenRequest request =  new TokenRequest(user.getUsername(), user.getPassword());
		//ResponseEntity<TokenResponse> response = restTemplate.postForEntity(AUTHENTICATION_URL, request, TokenResponse.class);
	    if(user != null) {
	        List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
	        return buildUserForAuthentication(user, authorities);
	    } else {
	        throw new UsernameNotFoundException("username not found");
	    }
	}
	
	private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
	    Set<GrantedAuthority> roles = new HashSet<>();
	    userRoles.forEach((role) -> {
	        roles.add(new SimpleGrantedAuthority(role.getRole()));
	    });

	    List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
	    return grantedAuthorities;
	}
	
	private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
	    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
	}

}
