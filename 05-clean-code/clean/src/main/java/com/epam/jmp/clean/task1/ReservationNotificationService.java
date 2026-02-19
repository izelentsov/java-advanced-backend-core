package com.epam.jmp.clean.task1;

public interface ReservationNotificationService {
    void userAddedToWaitingList(BookId bookId, UserId userId);
    void userRemovedFromWaitingList(BookId bookId, UserId userId);
    void userCanNowCheckOut(BookId bookId, UserId userId);
}

