package com.epam.jmp.rest.repository;


import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.epam.jmp.rest.entity.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;



@SpringBootTest(webEnvironment = NONE)
@Transactional
public class UserRepositoryTest {


    @Autowired
    private UserRepository repository;



    @Test
    public void savesAndFinds() {
        User initial = new User("Donald", "Knuth", "US");

        User saved = repository.save(initial);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(initial.getFirstName(), saved.getFirstName());
        assertEquals(initial.getLastName(), saved.getLastName());
        assertEquals(initial.getLocation(), saved.getLocation());


        Optional<User> maybeFound = repository.findById(saved.getId());

        assertNotNull(maybeFound);
        assertTrue(maybeFound.isPresent());
        User found = maybeFound.get();
        assertEquals(saved.getId(), found.getId());
        assertEquals(saved.getFirstName(), found.getFirstName());
        assertEquals(saved.getLastName(), found.getLastName());
        assertEquals(saved.getLocation(), found.getLocation());
    }


    @Test
    public void doesNotFindNonExisting() {
        Optional<User> maybeFound = repository.findById(123L);

        assertNotNull(maybeFound);
        assertTrue(maybeFound.isEmpty());
    }


    @Test
    public void deletesExisting() {
        User initial = new User("Donald", "Knuth", "US");
        User saved = repository.save(initial);
        repository.deleteById(saved.getId());

        Optional<User> maybeFound = repository.findById(saved.getId());
        assertNotNull(maybeFound);
        assertTrue(maybeFound.isEmpty());
    }


    @Test
    public void handlesDeleteNonExisting() {
        repository.deleteById(123L);
        // Does not throw
    }


    @Test
    public void findsAllByLocation() {
        repository.save(new User("Donald", "Knuth", "US"));
        repository.save(new User("Alan", "Turing", "UK"));
        repository.save(new User("Grace", "Hopper", "US"));

        assertEquals(2, repository.findAllByLocation("US").size());
        assertEquals(1, repository.findAllByLocation("UK").size());
        assertEquals(0, repository.findAllByLocation("DE").size());
    }

}
