package com.example.livesystem.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.livesystem.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	List<Book> findAll();
	
}
