package com.epam.jmp.dto;


public record DebitBankCard(CardNumber number, User user, double balance) implements BankCard {

}
