package com.epam.jmp.rest.service;


import com.epam.jmp.rest.model.UserCommand.UserCreateCommand;
import com.epam.jmp.rest.model.UserCommand.UserDeleteCommand;
import com.epam.jmp.rest.model.UserCommand.UserLocationUpdateCommand;


public interface UserCommandService {

    long createUser(UserCreateCommand command) throws UserException;
    void updateUserLocation(UserLocationUpdateCommand command) throws UserException;
    void deleteUser(UserDeleteCommand command) throws UserException;

}
