package com.implicit.server.configurer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("user_1").password("123456")
				.authorities("USER").build());
		return manager;
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	protected void configure(HttpSecurity http) throws Exception {
		http.requestMatchers()
				.antMatchers("/", "/login", "/oauth/authorize")
				.and()
				.authorizeRequests()
				.anyRequest()
				.permitAll()
				.and()
				.formLogin()
				.loginPage("/login")
				.and()
				.httpBasic()
				.disable()
				.exceptionHandling()
				.accessDeniedPage("/login?authorization_error=true")
				.and()
				.csrf()
				.requireCsrfProtectionMatcher(
						new AntPathRequestMatcher("/oauth/authorize"))
				.disable();
	}
}
