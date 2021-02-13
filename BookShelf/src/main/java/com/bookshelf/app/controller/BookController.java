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
import com.bookshelf.app.model.Book;
import com.bookshelf.app.repository.BookRepository;

@RestController
@RequestMapping("/api/v1/books/")
public class BookController {
	
	@Autowired
	BookRepository bookRepository;
	
	@GetMapping("/")
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_LIBRARIAN')")
	public List<Book> getAllBooks(){
		System.out.println("Getting it");
		return bookRepository.findAll();
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_LIBRARIAN')")
	public ResponseEntity<Book> getBookById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException{
		Book book = bookRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Book not found with id :: " + id));
					
		return ResponseEntity.ok().body(book);
	}
	
	@PostMapping("/")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LIBRARIAN')")
	public Book addNewBook(@Validated @RequestBody Book book) {
		return bookRepository.save(book);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LIBRARIAN')")
	public ResponseEntity<Book> updateBook(@PathVariable(value ="id") Long id,
			@Validated @RequestBody Book bookDetails) throws ResourceNotFoundException {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book not found with id :: " + id));
		book.setAuthor(bookDetails.getAuthor());
		book.setDescription(bookDetails.getDescription());
		book.setIsbn(bookDetails.getIsbn());
		book.setTitle(bookDetails.getTitle());
		final Book updatedBook = bookRepository.save(book);
		return ResponseEntity.ok(book);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LIBRARIAN')")
	public Map<String, Boolean> deleteBook(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book not found with id :: " + id));
		bookRepository.delete(book);
		Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
	}
	


}
