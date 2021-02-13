package com.bookshelf.app.jwt;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
public class LogoutRequest implements LogoutHandler{

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		System.out.println("In logout handler");
		Cookie cookie[] = request.getCookies();
        for(Cookie c : cookie) {
        	System.out.println(c.getName());
        	c.setMaxAge(0);
        	response.addCookie(c);
        }
	}

}
