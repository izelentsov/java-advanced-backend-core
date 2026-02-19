package com.epam.jmp.clean.task1.service;

import com.epam.jmp.clean.task1.model.BookId;
import com.epam.jmp.clean.task1.model.UserId;


public interface ReservationNotificationService {
    void userAddedToWaitingList(BookId bookId, UserId userId);
    void userRemovedFromWaitingList(BookId bookId, UserId userId);
    void userCanNowCheckOut(BookId bookId, UserId userId);
}

