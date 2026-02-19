package com.epam.jmp.clean.task1;

import java.util.HashMap;
import java.util.Map;



public class InMemoryBookRegistry implements BookRegistry {
    private final Map<BookId, UserId> bookRegistry = new HashMap<>();


    public InMemoryBookRegistry() {
    }

    @Override
    public boolean isBookCheckedOut(BookId bookId) {
        return bookRegistry.containsKey(bookId);
    }

    @Override
    public void checkOutBook(BookId bookId, UserId userId) {
        bookRegistry.put(bookId, userId);
    }

    @Override
    public void returnBook(BookId bookId) {
        bookRegistry.remove(bookId);
    }
}
