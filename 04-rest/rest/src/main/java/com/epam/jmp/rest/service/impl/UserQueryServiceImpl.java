package com.epam.jmp.rest.service.impl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.epam.jmp.rest.entity.User;
import com.epam.jmp.rest.model.UserQuery.FindAllUsersQuery;
import com.epam.jmp.rest.model.UserQuery.FindUserQuery;
import com.epam.jmp.rest.model.UserQuery.FindUsersByLocationQuery;
import com.epam.jmp.rest.repository.UserRepository;
import com.epam.jmp.rest.service.UserException;
import com.epam.jmp.rest.service.UserException.UserNotFoundException;
import com.epam.jmp.rest.service.UserException.UserQueryFailedException;
import com.epam.jmp.rest.service.UserQueryService;


@Service
public class UserQueryServiceImpl implements UserQueryService {

    private static final Logger LOG = LoggerFactory.getLogger(UserQueryServiceImpl.class);

    private final UserRepository repository;


    public UserQueryServiceImpl(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public User findUser(FindUserQuery query) throws UserException {
        try {
            return repository.findById(query.id())
                    .orElseThrow(() -> new UserNotFoundException("User not found", query.id()));
        } catch (UserException e) {
            throw e;
        } catch (Exception e) {
            LOG.error("Failed to find user: {}", query, e);
            throw new UserQueryFailedException("Failed to find user", query, e);
        }
    }


    @Override
    public List<User> findAllUsers(FindAllUsersQuery query) throws UserException {
        try {
            return repository.findAll();
        } catch (Exception e) {
            LOG.error("Failed to find all users: {}", query, e);
            throw new UserQueryFailedException("Failed to find users", query, e);
        }
    }

    @Override
    public List<User> findUsersByLocation(FindUsersByLocationQuery query) throws UserException {
        try {
            return repository.findAllByLocation(query.location());
        } catch (Exception e) {
            LOG.error("Failed to find users: {}", query, e);
            throw new UserQueryFailedException("Failed to find users", query, e);
        }
    }
}
