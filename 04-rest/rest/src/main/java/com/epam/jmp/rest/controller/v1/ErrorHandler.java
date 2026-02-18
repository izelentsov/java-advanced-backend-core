package com.epam.jmp.rest.controller.v1;


import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.epam.jmp.rest.dto.UserErrorResponse;
import com.epam.jmp.rest.model.UserCommand.UserCreateCommand;
import com.epam.jmp.rest.model.UserCommand.UserDeleteCommand;
import com.epam.jmp.rest.model.UserCommand.UserLocationUpdateCommand;
import com.epam.jmp.rest.model.UserQuery.FindUserQuery;
import com.epam.jmp.rest.service.UserException.UserCommandFailedException;
import com.epam.jmp.rest.service.UserException.UserNotFoundException;
import com.epam.jmp.rest.service.UserException.UserQueryFailedException;



@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> queryError(UserQueryFailedException e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        Instant ts = Instant.now();
        UserErrorResponse response =
                (e.query instanceof FindUserQuery(long id))
                        ? new UserErrorResponse(id, "Failed to find users", ts)
                        : new UserErrorResponse(e.getMessage(), ts);
        return new ResponseEntity<>(response, status);
    }


    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> commandError(UserCommandFailedException e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        Instant ts = Instant.now();
        UserErrorResponse response =
                (switch (e.command) {
                    case UserCreateCommand _ ->
                            new UserErrorResponse(e.getMessage(), ts);
                    case UserLocationUpdateCommand c ->
                            new UserErrorResponse(c.id(), e.getMessage(), ts);
                    case UserDeleteCommand c ->
                            new UserErrorResponse(c.id(), e.getMessage(), ts);
                });
        return new ResponseEntity<>(response, status);
    }


    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleNotFound(UserNotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        Instant ts = Instant.now();
        UserErrorResponse response = new UserErrorResponse(e.userId, e.getMessage(), ts);
        return new ResponseEntity<>(response, status);
    }


    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleOtherError(Exception e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        Instant ts = Instant.now();
        UserErrorResponse response = new UserErrorResponse(e.getMessage(), ts);
        return new ResponseEntity<>(response, status);
    }

}
