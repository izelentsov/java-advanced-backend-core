package com.epam.jmp.clean.task1.demo;

import com.epam.jmp.clean.task1.ReservableLibrarySystem;
import com.epam.jmp.clean.task1.model.BookCheckout;
import com.epam.jmp.clean.task1.model.BookId;
import com.epam.jmp.clean.task1.model.UserId;
import com.epam.jmp.clean.task1.repository.BookRegistry;
import com.epam.jmp.clean.task1.repository.InMemoryBookRegistry;
import com.epam.jmp.clean.task1.repository.InMemoryWaitingListRegistry;
import com.epam.jmp.clean.task1.repository.WaitingListRegistry;
import com.epam.jmp.clean.task1.service.ConsoleNotificationService;
import com.epam.jmp.clean.task1.service.ConsoleReservationNotificationService;
import com.epam.jmp.clean.task1.service.NotificationService;
import com.epam.jmp.clean.task1.service.ReservationNotificationService;

import java.util.Optional;

public class ReservableLibrarySystemDemo {
    public static void main(String[] args) {
        NotificationService notificationService = new ConsoleNotificationService();
        ReservationNotificationService reservationNotificationService = new ConsoleReservationNotificationService();
        BookRegistry bookRegistry = new InMemoryBookRegistry();
        WaitingListRegistry waitingListRegistry = new InMemoryWaitingListRegistry();
        ReservableLibrarySystem library = new ReservableLibrarySystem(
                bookRegistry, notificationService, waitingListRegistry, reservationNotificationService);

        BookId bookId = new BookId("BK100");
        UserId user1 = new UserId("USR1");
        UserId user2 = new UserId("USR2");
        UserId user3 = new UserId("USR3");

        // User 1 checks out the book
        Optional<BookCheckout> checkout1 = library.checkOutBook(bookId, user1);
        System.out.println("User1 checkout: " + (checkout1.isPresent() ? "success" : "fail"));

        // User 2 tries to check out, should be added to waiting list
        Optional<BookCheckout> checkout2 = library.checkOutBook(bookId, user2);
        System.out.println("User2 checkout: " + (checkout2.isPresent() ? "success" : "fail"));

        // User 3 tries to check out, should be added to waiting list
        Optional<BookCheckout> checkout3 = library.checkOutBook(bookId, user3);
        System.out.println("User3 checkout: " + (checkout3.isPresent() ? "success" : "fail"));

        // User 1 returns the book
        if (checkout1.isPresent()) {
            boolean returned = library.returnBook(checkout1.get());
            System.out.println("User1 return: " + (returned ? "success" : "fail"));
        }

        // User 2 (first in waiting list) checks out the book
        Optional<BookCheckout> checkout2b = library.checkOutBook(bookId, user2);
        System.out.println("User2 checkout after waiting: " + (checkout2b.isPresent() ? "success" : "fail"));
    }
}

