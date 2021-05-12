package com.project.library_management.controller;

import com.project.library_management.model.Book;
import com.project.library_management.model.IssuedBook;
import com.project.library_management.model.User;
import com.project.library_management.service.IssuedBookService;
import com.project.library_management.util.IssuedBooksValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class IssuedBooksController {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Autowired
    private IssuedBookService issuedBookService;

    IssuedBooksValidator issuedBooksValidator=new IssuedBooksValidator();

    //Get all the issued books
    @GetMapping("/issuedBooks")
    List<IssuedBook> findAllIssuedBooks() {
        return issuedBookService.findAllIssuedBooks();
    }

    //issue new book
    @PostMapping(value = "/issueBook")
    @ResponseStatus(HttpStatus.CREATED)
    public IssuedBook issueBook(@RequestBody IssuedBook issuedBook){
        if (issuedBooksValidator.isValid(issuedBook)) {
            String msg=validBooksAndUserLimits(issuedBook);

            if(msg.equals("valid"))
            return issuedBookService.insertIssuedBook(issuedBook);
            else {
                LOGGER.severe(msg);
                return null;
            }
        }
        else {
            LOGGER.severe("Inputs are not valid");
            return null;
        }
    }

//    //To check if books stock is empty or user exceeding his/her limit
    private String validBooksAndUserLimits(IssuedBook issuedBook){
        String msg="";
        if(!issuedBookService.bookLimitValid(issuedBook))
            msg=msg+"Book Stock is empty ";
        if(!issuedBookService.userBookLimit(issuedBook))
            msg=msg+"Your limit is exceeded";
        if(msg=="") msg="valid";

        return msg;

    }

    //search books issued by user
    @GetMapping(value = "/searchIssuedBooksByUser")
    public List<Integer> searchIssuedBooksByUser(@RequestParam(value = "q") int userId){
        LOGGER.info("searchIssuedBooksByUser called with userId "+userId);
        return issuedBookService.findBooksByUserId(userId);
    }

    //search users who have this books
    @GetMapping(value = "/searchUsersByBook")
    public List<Integer> searchUsersByBook(@RequestParam(value = "q") int bookId){
        LOGGER.info("searchUsersByBook called with bookId "+bookId);
        return issuedBookService.findUsersByBookId(bookId);
    }

    //delete a issued book after returned
    @Transactional
    @DeleteMapping(value = "/deleteIssuedBook/{userId}/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteIssuedBook(@PathVariable int userId, @PathVariable int bookId)
    {
        LOGGER.info("delete issued book with userId"+userId+" and bookId"+bookId);
        issuedBookService.deleteIssuedBook(userId,bookId);
    }



}
