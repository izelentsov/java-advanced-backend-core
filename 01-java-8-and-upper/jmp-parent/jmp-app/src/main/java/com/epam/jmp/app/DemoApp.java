package com.epam.jmp.app;

import java.time.LocalDate;

import com.epam.jmp.bank.Bank;
import com.epam.jmp.cloud.bank.impl.BankImpl;
import com.epam.jmp.cloud.service.impl.ServiceImpl;
import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.BankCardType;
import com.epam.jmp.dto.User;
import com.epam.jmp.service.Service;

/**
 * Demo application to showcase bank card creation and service subscription.
 */
public class DemoApp {


    static void main() {
        // create a bank
        Bank bank = new BankImpl();
        // create two users
        var user1 = new User("John", "Doe", LocalDate.now().minusYears(30));
        var user2 = new User("Jane", "Smith", LocalDate.now().minusYears(25));

        // create three cards of different types in the bank: two for the first user, and one for the second
        BankCard card1 = bank.createBankCard(user1, BankCardType.CREDIT);
        BankCard card2 = bank.createBankCard(user1, BankCardType.DEBIT);
        BankCard card3 = bank.createBankCard(user2, BankCardType.CREDIT);
        // print cards
        System.out.println("Created Bank Cards:");
        System.out.println(" - " + card1);
        System.out.println(" - " + card2);
        System.out.println(" - " + card3);

        // create a service
        Service service = new ServiceImpl();
        // subscribe all cards in the service
        service.subscribe(card1);
        service.subscribe(card2);
        service.subscribe(card3);

        // print subscriptions for all cards obtained from the service
        System.out.println("\nSubscriptions:");
        service.getSubscriptionByBankCardNumber(card1.number())
                .ifPresent(sub -> IO.println(" - " + sub));
        service.getSubscriptionByBankCardNumber(card2.number())
                .ifPresent(sub -> IO.println(" - " + sub));
        service.getSubscriptionByBankCardNumber(card3.number())
                .ifPresent(sub -> IO.println(" - " + sub));

        // print all users registered with the service
        System.out.println("\nUsers registered with the service:");
        service.getAllUsers()
                .forEach(user -> IO.println(" - " + user));

    }
}
