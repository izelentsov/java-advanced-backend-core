package com.epam.jmp.rest.repository;


import java.util.List;
import java.util.Optional;

import com.epam.jmp.rest.entity.User;




public interface UserRepository {

    User save(User user);
    Optional<User> findById(Long id);
    List<User> findAll();
    List<User> findAllByLocation(String location);
    void deleteById(Long id);

}
