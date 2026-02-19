package com.epam.jmp.clean.task1;

public interface BookStorage {
    boolean isBookAvailable(BookId bookId);
    void checkOutBook(BookId bookId, UserId userId);
    void returnBook(BookId bookId);
}
