package com.epam.jmp.clean.task1.service;


import com.epam.jmp.clean.task1.model.BookId;
import com.epam.jmp.clean.task1.model.UserId;

public interface NotificationService {
    void bookCheckedOut(BookId bookId, UserId userId);
    void bookCheckoutFailed(BookId bookId, UserId userId);
    void bookReturned(BookId bookId);
    void bookReturnFailed(BookId bookId);
}
