package com.epam.jmp.clean.task1;


import java.util.Optional;

public class LibrarySystemDemo {
    public static void main(String[] args) {
        NotificationService notificationService = new ConsoleNotificationService();
        BookRegistry bookStorage = new InMemoryBookRegistry();
        LibrarySystem library = new LibrarySystem(bookStorage, notificationService);
        BookId bookId = new BookId("BK001");
        UserId userId = new UserId("USR001");
        Optional<BookCheckout> result = library.checkOutBook(bookId, userId);
        if (result.isPresent()) {
            System.out.println("Checkout successful: Book ID = " + result.get().bookId() + ", User ID = " + result.get().userId());
            library.returnBook(result.get());
        } else {
            System.out.println("Checkout failed: Book is unavailable.");
        }
    }
}
