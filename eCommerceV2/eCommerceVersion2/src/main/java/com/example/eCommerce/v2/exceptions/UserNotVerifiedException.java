package com.example.eCommerce.v2.exceptions;

public class UserNotVerifiedException extends Exception{
    private boolean newEmailSent;

    public UserNotVerifiedException(boolean newEmailSent) {
        this.newEmailSent = newEmailSent;
    }

    public boolean isNewEmailSent() {
        return newEmailSent;
    }

}
