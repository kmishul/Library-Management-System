package com.project.library_management.service;

import com.project.library_management.model.Book;
import com.project.library_management.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<Book> findAllBooks() {
    return  bookRepository.findAll();
    }

    public Book insertBook(Book newBook) {
    return bookRepository.save(newBook);
    }

    public List<Book> findBooksByAuthor(String author) {
    return bookRepository.findBooksByAuthor(author);
    }

    public List<Book> findBooksBySubject(String subject) {
    return bookRepository.findBooksBySubject(subject);
    }
}
