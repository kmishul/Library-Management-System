package com.project.library_management.util;

import com.project.library_management.model.IssuedBook;

public class IssuedBooksValidator {

    public boolean isValid(IssuedBook issuedBook) {
        if (issuedBook.getBookId() < 0 || issuedBook.getUserId() < 0)
            return false;
        return true;
    }
}