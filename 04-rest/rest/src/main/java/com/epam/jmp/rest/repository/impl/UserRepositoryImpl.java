package com.epam.jmp.rest.repository.impl;


import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.epam.jmp.rest.entity.User;
import com.epam.jmp.rest.repository.UserRepository;



@Repository
public interface UserRepositoryImpl
        extends UserRepository, ListCrudRepository<User, Long> {

}
