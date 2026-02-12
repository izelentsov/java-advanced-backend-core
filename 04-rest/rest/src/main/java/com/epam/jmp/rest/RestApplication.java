package com.epam.jmp.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;



@SpringBootApplication
public class RestApplication {

	static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}



	@Bean
	public SecurityFilterChain mainFilterChain(HttpSecurity http) {
		http.securityMatcher("/**")
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(authz -> authz
						.requestMatchers("/v1/**").hasRole("USER")
						.anyRequest().denyAll())
				.httpBasic(_ -> {});
		return http.build();
	}


	@Bean
	public UserDetailsService userDetailsService() {
		var user = org.springframework.security.core.userdetails.User
				.withUsername("testuser")
				.password("{noop}pass")
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user);
	}
}
