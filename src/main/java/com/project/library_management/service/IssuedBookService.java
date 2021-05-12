package com.project.library_management.service;

import com.project.library_management.model.Book;
import com.project.library_management.model.IssuedBook;
import com.project.library_management.model.User;
import com.project.library_management.repository.BookRepository;
import com.project.library_management.repository.IssuedBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssuedBookService {

    @Autowired
    private IssuedBookRepository issuedBookRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<IssuedBook> findAllIssuedBooks() {
    return issuedBookRepository.findAll();
    }

    public IssuedBook insertIssuedBook(IssuedBook issuedBook) {
        return issuedBookRepository.save(issuedBook);
    }

    public List<Integer> findBooksByUserId(int userId) {
        return issuedBookRepository.findBooksByUserId(userId);
    }

    public List<Integer> findUsersByBookId(int bookId) {
        return issuedBookRepository.findUsersByBookId(bookId);
    }

    public void deleteIssuedBook(int userId, int bookId) {
        issuedBookRepository.deleteIssuedBookByBookIdAndUserId(bookId,userId);
    }

    public boolean bookLimitValid(IssuedBook issuedBook) {
        int booksIssued=issuedBookRepository.countIssuedBooksByBookId(issuedBook.getBookId());
        int totalBooks=bookRepository.countBooksById(issuedBook.getBookId());
        if(booksIssued<totalBooks) return true;
        return false;
    }

    public boolean userBookLimit(IssuedBook issuedBook) {
        int userBooks=issuedBookRepository.countIssuedBooksByUserId(issuedBook.getUserId());
        if(userBooks<2) return true;
        return false;
    }
}
