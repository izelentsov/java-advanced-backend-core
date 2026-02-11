package com.epam.jmp.cloud.bank.app;

import java.time.LocalDate;

import com.epam.jmp.dto.BankCardType;
import com.epam.jmp.dto.User;
import com.epam.jmp.cloud.bank.impl.BankImpl;
import com.epam.jmp.bank.Bank;

/**
 * Hello world!
 *
 */
public class App {
    void main() {
        Bank bank = new BankImpl();
        var user1 = new User("John", "Doe", LocalDate.now().minusYears(30));
        var user2 = new User("Alice", "Chains", LocalDate.now().minusYears(20));

        IO.println(bank.createBankCard(user1, BankCardType.CREDIT));
        IO.println(bank.createBankCard(user2, BankCardType.DEBIT));
    }
}
