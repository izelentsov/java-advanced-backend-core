package com.epam.jmp.dto;


public record CreditBankCard(CardNumber number, User user, double creditLimit) implements BankCard {

}
