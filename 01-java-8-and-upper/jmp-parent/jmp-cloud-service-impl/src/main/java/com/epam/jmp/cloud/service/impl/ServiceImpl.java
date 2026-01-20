package com.epam.jmp.cloud.service.impl;

import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.CardNumber;
import com.epam.jmp.dto.Subscription;
import com.epam.jmp.dto.User;
import com.epam.jmp.service.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;


public class ServiceImpl implements Service {


    private final Map<CardNumber, Subscription> subscriptions = new HashMap<>();
    private final Set<User> users = new HashSet<>();

    @Override
    public void subscribe(BankCard bankCard) {
        final LocalDate subStart = LocalDate.now();
        final Subscription sub = new Subscription(bankCard.number(), subStart);
        subscriptions.put(bankCard.number(), sub);
        users.add(bankCard.user());
    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(CardNumber cardNumber) {
        return Optional.ofNullable(subscriptions.get(cardNumber));
    }

    @Override
    public List<User> getAllUsers() {
        return users.stream().toList();
    }
}
