package com.project.library_management.util;

import com.project.library_management.model.User;

public class UserValidator {

    public static boolean isValidUser(User user) {
        if(user.getEmail()==null || user.getEmail()=="" || user.getName()==null || user.getName()=="" || user.getMobile()==null || user.getMobile()=="")
            return false;
        else return true;
    }
}
