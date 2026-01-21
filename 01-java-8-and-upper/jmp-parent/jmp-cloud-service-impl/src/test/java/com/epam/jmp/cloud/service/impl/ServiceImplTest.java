package com.epam.jmp.cloud.service.impl;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.epam.jmp.dto.CardNumber;
import com.epam.jmp.dto.DebitBankCard;
import com.epam.jmp.dto.Subscription;
import com.epam.jmp.dto.User;

import static org.junit.jupiter.api.Assertions.*;


public class ServiceImplTest {



    @Test
    public void subscribesDebitCard() {
        var service = new ServiceImpl();
        var user = new User("Alice", "Johnson", LocalDate.now().minusYears(28));
        var cardNumber = new CardNumber("1234567812345678");
        var card = new DebitBankCard(cardNumber, user, 10.0);

        service.subscribe(card);

        LocalDate d1 = LocalDate.now();
        Optional<Subscription> maybeSub = service.getSubscriptionByBankCardNumber(cardNumber);
        LocalDate d2 = LocalDate.now();

        assertNotNull(maybeSub);
        assertTrue(maybeSub.isPresent());

        maybeSub.ifPresent(sub -> {
            assertEquals(cardNumber, sub.bankcard());
            assertTrue(d1.equals(sub.startDate()) || d2.equals(sub.startDate()));
        });
    }


    @Test
    public void subscribesCreditCard() {
        var service = new ServiceImpl();
        var user = new User("Bob", "Williams", LocalDate.now().minusYears(35));
        var cardNumber = new CardNumber("8765432187654321");
        var card = new DebitBankCard(cardNumber, user, 0.0);

        service.subscribe(card);

        LocalDate d1 = LocalDate.now();
        Optional<Subscription> maybeSub = service.getSubscriptionByBankCardNumber(cardNumber);
        LocalDate d2 = LocalDate.now();

        assertNotNull(maybeSub);
        assertTrue(maybeSub.isPresent());

        maybeSub.ifPresent(sub -> {
            assertEquals(cardNumber, sub.bankcard());
            assertTrue(d1.equals(sub.startDate()) || d2.equals(sub.startDate()));
        });
    }


    @Test
    public void registersCardUsers() {
        var service = new ServiceImpl();
        var user1 = new User("Charlie", "Brown", LocalDate.now().minusYears(40));
        var user2 = new User("Diana", "Prince", LocalDate.now().minusYears(30));
        var card1 = new DebitBankCard(new CardNumber("1111222233334444"), user1, 5.0);
        var card2 = new DebitBankCard(new CardNumber("5555666677778888"), user1, 15.0);
        var card3 = new DebitBankCard(new CardNumber("9999000011112222"), user2, 0.0);

        service.subscribe(card1);
        service.subscribe(card2);
        service.subscribe(card3);

        var allUsers = service.getAllUsers();

        assertEquals(2, allUsers.size());
        assertTrue(allUsers.contains(user1));
        assertTrue(allUsers.contains(user2));
    }

}