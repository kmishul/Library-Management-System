package com.project.library_management.util;

import com.project.library_management.model.Book;

public class BookValidator {

    public boolean isValid(Book book){

        if(book.getTitle()=="" || book.getTitle()==null)
            return false;
        if(book.getAuthor()=="" || book.getAuthor()==null)
            return false;
        if(book.getSubject()=="" || book.getSubject()==null)
            return false;
        return true;
    }
}
