package com.epam.jmp.clean.task1;

public interface BookRegistry {
    boolean isBookCheckedOut(BookId bookId);
    void checkOutBook(BookId bookId, UserId userId);
    void returnBook(BookId bookId);
}
