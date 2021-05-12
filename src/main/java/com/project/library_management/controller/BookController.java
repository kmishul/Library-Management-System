package com.project.library_management.controller;

import com.project.library_management.exception.BookNotFoundException;
import com.project.library_management.model.Book;
import com.project.library_management.service.BookService;
import com.project.library_management.util.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class BookController {
    private final static Logger LOGGER=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Autowired
    private BookService bookService;

    BookValidator bookValidator=new BookValidator();

    // Find All books
    @GetMapping("/books")
    List<Book> findAllBooks() {
        LOGGER.info("findAllBooks called");
        List<Book> list ;

        try {
            list = bookService.findAllBooks();
            if (list.size() == 0) {
                LOGGER.severe("No book found");
                throw new BookNotFoundException(5);
            }
        }
        catch(BookNotFoundException e)
        {
            LOGGER.severe(e.toString());
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No Book Found", e);
        }
        return list;
    }

    //Create new book
    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    Book newBook(@RequestBody Book newBook) {

        if (bookValidator.isValid(newBook))
            return bookService.insertBook(newBook);
        else {
            LOGGER.severe("NewBook is not valid");
            return null;
        }
    }

    //Find books by Author name
    @GetMapping(value = "/searchBooksByAuthor")
    public List<Book> searchBooksByAuthor(@RequestParam(value = "q") String author){
        return bookService.findBooksByAuthor(author);
    }

    //Find books by Subject name
    @GetMapping(value = "/searchBooksBySubject")
    public List<Book> searchBooksBySubject(@RequestParam(value = "q") String subject){
        return bookService.findBooksBySubject(subject);
    }
}