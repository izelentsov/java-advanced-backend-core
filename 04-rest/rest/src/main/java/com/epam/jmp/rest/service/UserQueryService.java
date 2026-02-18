package com.epam.jmp.rest.service;


import java.util.List;

import com.epam.jmp.rest.entity.User;
import com.epam.jmp.rest.model.UserQuery.FindAllUsersQuery;
import com.epam.jmp.rest.model.UserQuery.FindUserQuery;
import com.epam.jmp.rest.model.UserQuery.FindUsersByLocationQuery;


public interface UserQueryService {

    User findUser(FindUserQuery query) throws UserException;
    List<User> findAllUsers(FindAllUsersQuery query) throws UserException;
    List<User> findUsersByLocation(FindUsersByLocationQuery query) throws UserException;

}
