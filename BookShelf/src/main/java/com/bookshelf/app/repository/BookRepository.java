package com.bookshelf.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookshelf.app.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
