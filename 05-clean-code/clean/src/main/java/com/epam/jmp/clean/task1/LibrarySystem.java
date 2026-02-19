package com.epam.jmp.clean.task1;

import java.util.Optional;




public class LibrarySystem {
    private final BookStorage bookStorage;
    private final NotificationService notificationService;

    public LibrarySystem(BookStorage bookStorage, NotificationService notificationService) {
        this.bookStorage = bookStorage;
        this.notificationService = notificationService;
    }

    public Optional<BookCheckout> checkOutBook(BookId bookId, UserId userId) {
        if (bookStorage.isBookAvailable(bookId)) {
            bookStorage.checkOutBook(bookId, userId);
            notificationService.notify("Book checked out to " + userId);
            return Optional.of(new BookCheckout(bookId, userId));
        } else {
            notificationService.notify("Book is currently unavailable.");
            return Optional.empty();
        }
    }

    public void returnBook(BookCheckout checkout) {
        if (bookStorage.isBookAvailable(checkout.bookId())) {
            notificationService.notify("This book was not checked out.");
        } else {
            // In the future, checks/validations can be added here using checkout metadata
            bookStorage.returnBook(checkout.bookId());
            notificationService.notify("Book returned.");
        }
    }
}
