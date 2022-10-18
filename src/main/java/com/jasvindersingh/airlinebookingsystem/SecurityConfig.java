package com.jasvindersingh.airlinebookingsystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {
                                          //antMatchers() configuring URL path & spring security permit request as per user role
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers( "/js/**","/css/**", "/images/**", "/favicon.ico").permitAll()  
		.antMatchers("/login","/register","/users/add").permitAll()
		.and().formLogin().loginPage("/login").defaultSuccessUrl("/index").permitAll()
		.and().logout().                                                                      //logout configuration
		logoutUrl("/logout"). 
		logoutSuccessUrl("/index")
		.and().csrf().disable();                   // csrf() is unique token per user session bases,is disable by spring security
		http.sessionManagement(session -> session      //  session management handles multiple requests for web based 
				                                       //application using spring security from a single user
            .maximumSessions(1)                        // Allowed max no of open session. Here max session per user allowed one 
            .maxSessionsPreventsLogin(true)            //    
        );
		http.logout(logout -> logout              // after logout delete cookies set during login
            .deleteCookies("JSESSIONID")          // 
        );
		return http.build();                    // build method returns new http request each time when it invoked
	}

}
