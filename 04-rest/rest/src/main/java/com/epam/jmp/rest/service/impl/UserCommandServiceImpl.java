package com.epam.jmp.rest.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.epam.jmp.rest.entity.User;
import com.epam.jmp.rest.model.UserCommand.UserCreateCommand;
import com.epam.jmp.rest.model.UserCommand.UserDeleteCommand;
import com.epam.jmp.rest.model.UserCommand.UserLocationUpdateCommand;
import com.epam.jmp.rest.repository.UserRepository;
import com.epam.jmp.rest.service.UserCommandService;
import com.epam.jmp.rest.service.UserException;
import com.epam.jmp.rest.service.UserException.UserCommandFailedException;
import com.epam.jmp.rest.service.UserException.UserNotFoundException;



@Service
public class UserCommandServiceImpl implements UserCommandService {


    private static final Logger LOG = LoggerFactory.getLogger(UserCommandServiceImpl.class);


    private final UserRepository repository;


    public UserCommandServiceImpl(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public long createUser(UserCreateCommand command) throws UserException {
        try {
            User newUser = new User(command.firstName(), command.lastName(), command.location());
            User created = repository.save(newUser);
            LOG.info("User created: {}", created);
            return created.getId();
        } catch (Exception e) {
            LOG.error("Failed to create user: {}", command, e);
            throw new UserCommandFailedException("Failed to create user", command, e);
        }
    }

    @Override
    public void updateUserLocation(UserLocationUpdateCommand command) throws UserException {
        User user = repository.findById(command.id()).orElseThrow(
                () -> new UserNotFoundException("User not found: " + command.id()));
        user.setLocation(command.newLocation());
        try {
            repository.save(user);
            LOG.info("User updated: {}", user);
        } catch (Exception e) {
            LOG.error("Failed to update user: {}", command, e);
            throw new UserCommandFailedException("Failed to update user", command, e);
        }
    }


    @Override
    public void deleteUser(UserDeleteCommand command) throws UserException{
        try {
            repository.deleteById(command.id());
            LOG.info("User deleted: {}", command);
        } catch (Exception e) {
            LOG.error("Failed to delete user: {}", command, e);
            throw new UserCommandFailedException("Failed to delete user", command, e);
        }

    }
}
