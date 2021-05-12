package com.project.library_management.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(int id) {super("User id not found : " + id);
    }
}
