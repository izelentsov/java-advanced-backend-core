package com.epam.jmp.clean.task1.demo;


import java.util.Optional;

import com.epam.jmp.clean.task1.LibrarySystem;
import com.epam.jmp.clean.task1.model.BookCheckout;
import com.epam.jmp.clean.task1.model.BookId;
import com.epam.jmp.clean.task1.model.UserId;
import com.epam.jmp.clean.task1.repository.BookRegistry;
import com.epam.jmp.clean.task1.repository.InMemoryBookRegistry;
import com.epam.jmp.clean.task1.service.ConsoleNotificationService;
import com.epam.jmp.clean.task1.service.NotificationService;



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
