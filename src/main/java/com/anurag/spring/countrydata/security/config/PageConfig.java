package com.anurag.spring.countrydata.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PageConfig implements WebMvcConfigurer {	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder passwordEncoder =  new BCryptPasswordEncoder();
		return passwordEncoder;
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
	    registry.addViewController("/home").setViewName("home");
	    registry.addViewController("/").setViewName("home");
	    registry.addViewController("/dashboard").setViewName("dashboard");
	    registry.addViewController("/login").setViewName("login");
	}
	
	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
		/*.inMemoryAuthentication().passwordEncoder(passwordEncoder)
									 .withUser("anurag.mishra")
									 .password(passwordEncoder.encode("qwe123"))
									 .roles("USER","ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/admin/**").hasAnyRole("ADMIN")
												 .anyRequest().hasAnyRole("USER").and()
							.formLogin()
								.loginPage("/login")
								.successForwardUrl("/dashboard")
								.permitAll();
	}*/

}
