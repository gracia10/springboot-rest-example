package com.rest.web.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		
        http
	        	.authorizeRequests()
	        	.antMatchers("/", "/login/**", "/css/**", "/images/**", "/js/**", "/console/**").permitAll()
	        	.anyRequest().authenticated()
            .and()
            	.formLogin()
            		.successForwardUrl("/board/list")
            .and()
            	.logout()
            		.logoutUrl("/logout")
            		.logoutSuccessUrl("/login")
            		.deleteCookies("JSESSIONID")
            		.invalidateHttpSession(true)
            .and()
            .csrf().disable();
	}

	@Bean
	InMemoryUserDetailsManager userDetailsManager() {
		User.UserBuilder commonUser = User.withUsername("commonUser").password("{noop}common").roles("USER");
		User.UserBuilder havi = User.withUsername("havi").password("{noop}test").roles("USER", "ADMIN");

		List<UserDetails> userDetailsList = new ArrayList<>();
		userDetailsList.add(commonUser.build());
		userDetailsList.add(havi.build());

		return new InMemoryUserDetailsManager(userDetailsList);
	}

}