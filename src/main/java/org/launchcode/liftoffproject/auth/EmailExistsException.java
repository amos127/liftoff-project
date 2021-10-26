package org.launchcode.liftoffproject.auth;

public class EmailExistsException extends Exception {

    public EmailExistsException(String message) {
        super(message);
    }

}
