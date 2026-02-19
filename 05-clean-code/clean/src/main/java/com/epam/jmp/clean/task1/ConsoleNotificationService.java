package com.epam.jmp.clean.task1;



public class ConsoleNotificationService implements NotificationService {

    @Override
    public void bookCheckedOut(BookId bookId, UserId userId) {
        System.out.println("Book " + bookId + " checked out to user " + userId);
    }

    @Override
    public void bookCheckoutFailed(BookId bookId, UserId userId) {
        System.out.println("Book " + bookId + " is currently unavailable for user " + userId);
    }

    @Override
    public void bookReturned(BookId bookId) {
        System.out.println("Book " + bookId + " returned.");
    }

    @Override
    public void bookReturnFailed(BookId bookId) {
        System.out.println("Book " + bookId + " was not checked out.");
    }
}
