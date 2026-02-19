package com.epam.jmp.clean.task1;



public interface NotificationService {
    void bookCheckedOut(BookId bookId, UserId userId);
    void bookCheckoutFailed(BookId bookId, UserId userId);
    void bookReturned(BookId bookId);
    void bookReturnFailed(BookId bookId);
}
