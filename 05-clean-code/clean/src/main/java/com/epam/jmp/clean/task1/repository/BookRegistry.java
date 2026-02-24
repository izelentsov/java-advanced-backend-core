package com.epam.jmp.clean.task1.repository;

import com.epam.jmp.clean.task1.model.BookId;
import com.epam.jmp.clean.task1.model.UserId;

public interface BookRegistry {
    boolean isBookCheckedOut(BookId bookId);
    void checkOutBook(BookId bookId, UserId userId);
    void returnBook(BookId bookId);
}
