package com.project.library_management.repository;

import com.project.library_management.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookRepository extends JpaRepository<Book,Integer> {

    List<Book> findBooksByAuthor(String author);
    List<Book> findBooksBySubject(String subject);
    int countBooksById(int bookId);
}
