package com.bookshelf.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bookshelf.app.model.BookShelfUser;
import com.bookshelf.app.model.BookShelfUserDetails;
import com.bookshelf.app.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public BookShelfUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<BookShelfUser> bookShelfUser = userRepository.findByUsername(username);
		bookShelfUser.orElseThrow(() -> new UsernameNotFoundException("User name not found" + username));
		UserDetails userObj = bookShelfUser .map(BookShelfUserDetails::new).get();
		System.out.println(userObj);
		return bookShelfUser.map(BookShelfUserDetails::new).get();
	}

}
