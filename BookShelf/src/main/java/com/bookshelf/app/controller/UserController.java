package com.bookshelf.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookshelf.app.exception.ResourceNotFoundException;
import com.bookshelf.app.model.BookShelfUser;
import com.bookshelf.app.repository.UserRepository;

@RestController
@RequestMapping("/api/v1/users/")
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public List<BookShelfUser> getAllUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<BookShelfUser> getUserById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException{
		BookShelfUser bookShelfUser = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id :: " + id));
		return ResponseEntity.ok(bookShelfUser);
	}
	
	@PostMapping("/")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public BookShelfUser addNewUser(@Validated @RequestBody BookShelfUser bookShelfUser){
		return userRepository.save(bookShelfUser);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<BookShelfUser> updateUser(@PathVariable(value ="id") Long id,
			@Validated @RequestBody BookShelfUser bookShelfUserDetails) throws ResourceNotFoundException{
		BookShelfUser bookShelfUser = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id :: " + id));
		bookShelfUser.setActive(bookShelfUserDetails.isActive());
		bookShelfUser.setPassword(bookShelfUserDetails.getPassword());
		bookShelfUser.setRoles(bookShelfUserDetails.getRoles());
		bookShelfUser.setUsername(bookShelfUserDetails.getUsername());
		final BookShelfUser updatedUser = userRepository.save(bookShelfUser);
		return ResponseEntity.ok(updatedUser);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long id) throws ResourceNotFoundException{
		BookShelfUser bookShelfUser = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id :: " + id));
		userRepository.delete(bookShelfUser);
		Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
	}
	
}
