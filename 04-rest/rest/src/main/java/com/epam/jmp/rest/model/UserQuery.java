package com.epam.jmp.rest.model;


import com.epam.jmp.rest.model.UserQuery.FindAllUsersQuery;
import com.epam.jmp.rest.model.UserQuery.FindUserQuery;
import com.epam.jmp.rest.model.UserQuery.FindUsersByLocationQuery;


public sealed interface UserQuery
        permits FindUserQuery,
        FindAllUsersQuery,
        FindUsersByLocationQuery {


    record FindUserQuery(long id) implements UserQuery {
    }

    record FindAllUsersQuery() implements UserQuery {
    }

    record FindUsersByLocationQuery(String location) implements UserQuery {
    }

}
