package com.epam.jmp.clean.task1.service;

import com.epam.jmp.clean.task1.model.BookId;
import com.epam.jmp.clean.task1.model.UserId;

public class ConsoleReservationNotificationService implements ReservationNotificationService {
    @Override
    public void userAddedToWaitingList(BookId bookId, UserId userId) {
        System.out.println("User " + userId + " added to waiting list for book " + bookId);
    }

    @Override
    public void userRemovedFromWaitingList(BookId bookId, UserId userId) {
        System.out.println("User " + userId + " removed from waiting list for book " + bookId);
    }

    @Override
    public void userCanNowCheckOut(BookId bookId, UserId userId) {
        System.out.println("User " + userId + " can now check out book " + bookId);
    }
}

