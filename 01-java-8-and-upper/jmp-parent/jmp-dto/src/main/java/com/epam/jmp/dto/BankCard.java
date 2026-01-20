package com.epam.jmp.dto;


public sealed interface BankCard
        permits CreditBankCard, DebitBankCard {

    CardNumber number();
    User user();
}
