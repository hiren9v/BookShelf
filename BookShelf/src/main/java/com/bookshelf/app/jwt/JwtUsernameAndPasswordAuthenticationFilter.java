package com.bookshelf.app.jwt;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private final AuthenticationManager authenticationManager;
    private final JwtConfigurations jwtConfigurations;
    private final SecretKey secretKey;

    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager,
                                                      JwtConfigurations jwtConfigurations,
                                                      SecretKey secretKey) {
        this.authenticationManager = authenticationManager;
        this.jwtConfigurations = jwtConfigurations;
        this.secretKey = secretKey;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        try {
            UsernameAndPasswordAuthenticationRequest authenticationRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), UsernameAndPasswordAuthenticationRequest.class);
            
            System.out.println("Into jwt filter");
            
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
            );

            Authentication authenticate = authenticationManager.authenticate(authentication);
            return authenticate;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfigurations.getTokenExpirationAfterDays())))
                .signWith(secretKey)
                .compact();
        
        Cookie cookie = new Cookie("JWTSession",token);
        cookie.setMaxAge(jwtConfigurations.getTokenExpirationAfterDays() * 24 * 60 * 60);
        //cookie.setSecure(true);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        
        Cookie userName = new Cookie("username", authResult.getName());
        userName.setMaxAge(jwtConfigurations.getTokenExpirationAfterDays() * 24 * 60 * 60);
        response.addCookie(userName);
        
        String authority = authResult.getAuthorities().toString();
        Cookie role = new Cookie("role", authority.substring(1,authority.length()-1));
        role.setMaxAge(jwtConfigurations.getTokenExpirationAfterDays() * 24 * 60 * 60);
        response.addCookie(role);
        
        
        
        
        //response.addHeader(jwtConfigurations.getAuthorizationHeader(), jwtConfigurations.getTokenPrefix() + token);
    }

}
