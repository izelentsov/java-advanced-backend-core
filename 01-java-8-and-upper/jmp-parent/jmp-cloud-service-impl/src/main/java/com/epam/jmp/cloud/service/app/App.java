package com.epam.jmp.cloud.service.app;

import java.time.LocalDate;

import com.epam.jmp.cloud.service.impl.ServiceImpl;
import com.epam.jmp.dto.CardNumber;
import com.epam.jmp.dto.CreditBankCard;
import com.epam.jmp.dto.DebitBankCard;
import com.epam.jmp.dto.User;
import com.epam.jmp.service.Service;


public class App {
    void main() {
        var user1 = new User("John", "Doe", LocalDate.now().minusYears(30));
        var card1 = new CreditBankCard(new CardNumber("5900876341244959"), user1, 100.0);
        var user2 = new User("Alice", "Chains", LocalDate.now().minusYears(20));
        var card2 = new DebitBankCard(new CardNumber("5167400059434727"), user2, 200.0);

        Service s = new ServiceImpl();
        s.subscribe(card1);
        s.subscribe(card2);

        IO.println(s.getSubscriptionByBankCardNumber(card1.number()));
        IO.println(s.getSubscriptionByBankCardNumber(card2.number()));
        IO.println(s.getAllUsers());
    }
}
