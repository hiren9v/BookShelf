package com.bookshelf.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookshelf.app.model.BookShelfUser;

@Repository
public interface UserRepository extends JpaRepository<BookShelfUser, Long>{

	Optional<BookShelfUser> findByUsername(String username);

}
