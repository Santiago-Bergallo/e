package com.example.eCommerce.v2.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends Exception{

    public String message() {
        return  "No user with Id: ";
    }

 }
