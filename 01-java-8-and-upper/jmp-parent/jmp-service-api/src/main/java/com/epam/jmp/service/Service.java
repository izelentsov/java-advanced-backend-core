package com.epam.jmp.service;

import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.CardNumber;
import com.epam.jmp.dto.Subscription;
import com.epam.jmp.dto.User;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;


public interface Service {

    int PAYABLE_AGE = 18;


    void subscribe(BankCard bankCard);

    Optional<Subscription> getSubscriptionByBankCardNumber(CardNumber cardNumber);

    // Task 11
    List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> condition);

    List<User> getAllUsers();


    // Task 7
    default double getAverageUsersAge() {
        LocalDate now = LocalDate.now();
        return getAllUsers().stream()
                .mapToInt(user -> userAge(user, now))
                .summaryStatistics()
                .getAverage();
    }


    // Task 8
    static boolean isPayableUser(User user) {
        return userAge(user, LocalDate.now()) >= PAYABLE_AGE;
    }


    private static int userAge(User user, LocalDate now) {
        return Period.between(user.birthday(), now).getYears();
    }
}
