package com.implicit.server.configurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import com.implicit.server.utils.Constants;

@Configuration
@EnableResourceServer
public class ResouceServerConfiguration extends ResourceServerConfigurerAdapter {
	@Override
	public void configure(ResourceServerSecurityConfigurer resources)
			throws Exception {
		resources.resourceId(Constants.RESOURCE_ID).stateless(true);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		 http.requestMatchers()
           .antMatchers("/api/**").and()
           .authorizeRequests().anyRequest().authenticated()
           .and().authorizeRequests()
           .antMatchers("/api/getinfo").access("#oauth2.hasScope('getInfo')");
	}

}
