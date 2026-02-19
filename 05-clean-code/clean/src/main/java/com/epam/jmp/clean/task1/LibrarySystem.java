package com.epam.jmp.clean.task1;

import java.util.Optional;




public class LibrarySystem {
    protected final BookRegistry bookRegistry;
    protected final NotificationService notificationService;

    public LibrarySystem(BookRegistry bookRegistry, NotificationService notificationService) {
        this.bookRegistry = bookRegistry;
        this.notificationService = notificationService;
    }

    public Optional<BookCheckout> checkOutBook(BookId bookId, UserId userId) {
        if (bookRegistry.isBookCheckedOut(bookId)) {
            notificationService.bookCheckoutFailed(bookId, userId);
            return Optional.empty();
        } else {
            bookRegistry.checkOutBook(bookId, userId);
            notificationService.bookCheckedOut(bookId, userId);
            return Optional.of(new BookCheckout(bookId, userId));
        }
    }

    public boolean returnBook(BookCheckout checkout) {
        if (bookRegistry.isBookCheckedOut(checkout.bookId())) {
            bookRegistry.returnBook(checkout.bookId());
            notificationService.bookReturned(checkout.bookId());
            return true;
        } else {
            notificationService.bookReturnFailed(checkout.bookId());
            return false;
        }
    }
}
