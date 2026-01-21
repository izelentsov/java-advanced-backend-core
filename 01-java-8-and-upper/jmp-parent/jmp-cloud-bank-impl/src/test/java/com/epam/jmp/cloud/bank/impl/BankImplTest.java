package com.epam.jmp.cloud.bank.impl;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.BankCardType;
import com.epam.jmp.dto.User;

import static org.junit.jupiter.api.Assertions.*;



class BankImplTest {


    @Test
    public void createsCreditCard() {
        var bank = new BankImpl();
        var user = new User("John", "Doe", LocalDate.now().minusYears(30));

        BankCard card = bank.createBankCard(user, BankCardType.CREDIT);

        assertNotNull(card);
        assertEquals(user, card.user());
        checkCardNumber(card);
    }


    @Test
    public void createsDebitCard() {
        var bank = new BankImpl();
        var user = new User("Jane", "Smith", LocalDate.now().minusYears(25));

        BankCard card = bank.createBankCard(user, BankCardType.DEBIT);

        assertNotNull(card);
        assertEquals(user, card.user());
        checkCardNumber(card);
    }


    private static void checkCardNumber(BankCard card) {
        String number = card.number().number();
        assertEquals(BankImpl.CARD_NUMBER_LENGTH, number.length());
        assertTrue(number.chars().allMatch(Character::isDigit));
    }
}