package com.epam.jmp.cloud.bank.impl;

import java.util.Random;
import java.util.stream.Collectors;

import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.BankCardType;
import com.epam.jmp.dto.CardNumber;
import com.epam.jmp.dto.CreditBankCard;
import com.epam.jmp.dto.DebitBankCard;
import com.epam.jmp.dto.User;
import com.epam.jmp.bank.Bank;


public class BankImpl implements Bank {

    public static final int CARD_NUMBER_LENGTH = 16;


    private final Random rand = new Random();

    @Override
    public BankCard createBankCard(User user, BankCardType cardType) {
        final CardNumber cardNumber = generateCardNumber();
        return CardFactory.forType(cardType)
                .create(cardNumber, user, 0.0);
    }


    // Task 12
    @FunctionalInterface
    private interface CardFactory {
        BankCard create(CardNumber number, User user, double initial);

        static CardFactory forType(BankCardType cardType) {
            return switch (cardType) {
                case DEBIT -> DebitBankCard::new;
                case CREDIT -> CreditBankCard::new;
            };
        }
    }

    private CardNumber generateCardNumber() {
        final String number = rand.ints(0, 10)
                .limit(CARD_NUMBER_LENGTH)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining());
        return new CardNumber(number);
    }
}
