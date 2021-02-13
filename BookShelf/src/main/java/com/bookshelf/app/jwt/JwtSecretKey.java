package com.bookshelf.app.jwt;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.security.Keys;

@Configuration
public class JwtSecretKey {
	
	private final JwtConfigurations jwtConfigurations;
	
	@Autowired
	public JwtSecretKey(JwtConfigurations jwtConfigurations) {
		this.jwtConfigurations = jwtConfigurations;
	}
	
    @Bean
    public SecretKey secretKey() {
        return Keys.hmacShaKeyFor(jwtConfigurations.getSecretKey().getBytes());
    }	
}
