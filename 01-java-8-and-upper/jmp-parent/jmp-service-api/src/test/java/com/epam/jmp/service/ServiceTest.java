package com.epam.jmp.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.CardNumber;
import com.epam.jmp.dto.Subscription;
import com.epam.jmp.dto.User;

import static org.junit.jupiter.api.Assertions.*;



class ServiceTest {

    @Test
    public void calculatesAverageAgeMultipleUsers() {
        Service service = userListService(
                new User("Alice", "Johnson", java.time.LocalDate.now().minusYears(30)),
                new User("Bob", "Smith", java.time.LocalDate.now().minusYears(40)));
        assertEquals(35.0, service.getAverageUsersAge());
    }

    @Test
    public void calculatesAverageAgeNoUsers() {
        Service service = userListService();
        assertEquals(0.0, service.getAverageUsersAge());
    }


    private static Service userListService(User... users) {
        return new Service() {
            @Override
            public void subscribe(BankCard bankCard) {
            }

            @Override
            public Optional<Subscription> getSubscriptionByBankCardNumber(CardNumber cardNumber) {
                return Optional.empty();
            }

            @Override
            public List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> condition) {
                return List.of();
            }

            @Override
            public List<User> getAllUsers() {
                return List.of(users);
            }
        };
    }
}