package com.epam.jmp.rest.service;


import com.epam.jmp.rest.model.UserCommand;
import com.epam.jmp.rest.model.UserQuery;

public class UserException extends Exception {

    private UserException(String message) {
        super(message);
    }

    private UserException(String message, Throwable cause) {
        super(message, cause);
    }



    public static class UserCommandFailedException extends UserException {
        public final UserCommand command;
        public UserCommandFailedException(String message, UserCommand command, Throwable cause) {
            super(message, cause);
            this.command = command;
        }
    }


    public static class UserQueryFailedException extends UserException {
        public final UserQuery query;
        public UserQueryFailedException(String message, UserQuery query, Throwable cause) {
            super(message, cause);
            this.query = query;
        }
    }


    public static class UserNotFoundException extends UserException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }
}
