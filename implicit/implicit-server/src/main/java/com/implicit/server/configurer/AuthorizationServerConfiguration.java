package com.implicit.server.configurer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import com.implicit.server.utils.Constants;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends
		AuthorizationServerConfigurerAdapter {

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	/**
	 * AuthorizationServerSecurityConfigurer：用来配置令牌端点(Token Endpoint)的安全约束.
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security)
			throws Exception {
		// AuthorizationEndpoint：用来作为请求者获得授权的服务，默认的URL是/oauth/authorize.
		// TokenEndpoint：用来作为请求者获得令牌（Token）的服务，默认的URL是/oauth/token.
		security.realm(Constants.RESOURCE_ID)
				.allowFormAuthenticationForClients();// allowFormAuthenticationForClients:主要是让/oauth/token支持client_id以及client_secret作登录认证
	}

	/**
	 * ClientDetailsServiceConfigurer：用来配置客户端详情服务（ClientDetailsService），
	 * 客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息
	 * clientId：（必须的）用来标识客户的Id。 secret：（需要值得信任的客户端）客户端安全码，如果有的话。
	 * scope：用来限制客户端的访问范围，如果为空（默认）的话，那么客户端拥有全部的访问范围。
	 * authorizedGrantTypes：此客户端可以使用的授权类型，默认为空。 authorities：此客户端可以使用的权限（基于Spring
	 * Security authorities）
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients)
			throws Exception {
		clients.inMemory().withClient("c_test")
				.resourceIds(Constants.RESOURCE_ID)
				.authorizedGrantTypes("authorization_code","implicit").authorities("USER")
				.scopes("getInfo").secret("123456")
				.redirectUris("http://localhost:8081/callback/redirect")
				.autoApprove(true);

	}

	/**
	 * AuthorizationServerEndpointsConfigurer：用来配置授权（authorization）以及令牌（token）
	 * 的访问端点和令牌服务(token services)。
	 * authenticationManager：认证管理器，当你选择了资源所有者密码（password）授权类型的时候，请设置这个属性注入一个
	 * AuthenticationManager 对象。
	 * 
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints)
			throws Exception {
		endpoints
				.tokenStore(new InMemoryTokenStore())
				.authenticationManager(authenticationManager)
				.allowedTokenEndpointRequestMethods(HttpMethod.GET,
						HttpMethod.POST);
	}

	@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
	}

}
