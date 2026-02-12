package com.epam.jmp.rest.controller.v1;


import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.jmp.rest.dto.UserCreatedResponse;
import com.epam.jmp.rest.dto.UserDeletedResponse;
import com.epam.jmp.rest.dto.UserUpdatedResponse;
import com.epam.jmp.rest.model.UserCommand.UserCreateCommand;
import com.epam.jmp.rest.model.UserCommand.UserDeleteCommand;
import com.epam.jmp.rest.model.UserCommand.UserLocationUpdateCommand;
import com.epam.jmp.rest.service.UserCommandService;
import com.epam.jmp.rest.service.UserException;


@RestController
@RequestMapping("/v1/user")
public class UserCommandController {


    private final UserCommandService userService;

    public UserCommandController(UserCommandService userService) {
        this.userService = userService;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserCreatedResponse> createUser(@RequestBody UserCreateCommand command)
            throws UserException {
        long userId = userService.createUser(command);
        UserCreatedResponse response = new UserCreatedResponse(userId, Instant.now());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserUpdatedResponse> updateUser(@RequestBody UserLocationUpdateCommand command)
        throws UserException {
        userService.updateUserLocation(command);
        UserUpdatedResponse response = new UserUpdatedResponse(command.id(), Instant.now());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDeletedResponse> deleteUser(@RequestBody UserDeleteCommand command)
        throws UserException {
        userService.deleteUser(command);
        UserDeletedResponse response = new UserDeletedResponse(command.id(), Instant.now());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
