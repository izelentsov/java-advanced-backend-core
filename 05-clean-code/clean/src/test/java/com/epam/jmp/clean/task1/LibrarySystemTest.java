package com.epam.jmp.clean.task1;

import org.junit.jupiter.api.Test;
import java.util.Optional;

import com.epam.jmp.clean.task1.model.BookCheckout;
import com.epam.jmp.clean.task1.model.BookId;
import com.epam.jmp.clean.task1.model.UserId;
import com.epam.jmp.clean.task1.repository.BookRegistry;
import com.epam.jmp.clean.task1.repository.InMemoryBookRegistry;
import com.epam.jmp.clean.task1.service.NotificationService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


class LibrarySystemTest {
    @Test
    void testSuccessfulCheckoutAndReturn() {
        BookId bookId = new BookId("BK001");
        UserId userId = new UserId("USR001");
        BookRegistry registry = new InMemoryBookRegistry();
        NotificationService notificationService = mock(NotificationService.class);
        LibrarySystem library = new LibrarySystem(registry, notificationService);

        Optional<BookCheckout> checkout = library.checkOutBook(bookId, userId);
        assertTrue(checkout.isPresent(), "Book should be checked out successfully");
        assertEquals(bookId, checkout.get().bookId());
        assertEquals(userId, checkout.get().userId());
        verify(notificationService).bookCheckedOut(bookId, userId);
        verify(notificationService, never()).bookCheckoutFailed(any(), any());

        boolean returned = library.returnBook(checkout.get());
        assertTrue(returned, "Book should be returned successfully");
        verify(notificationService).bookReturned(bookId);
        verify(notificationService, never()).bookReturnFailed(any());
    }

    @Test
    void testCheckoutUnavailableBook() {
        BookId bookId = new BookId("BK002");
        UserId userId1 = new UserId("USR001");
        UserId userId2 = new UserId("USR002");
        BookRegistry registry = new InMemoryBookRegistry();
        NotificationService notificationService = mock(NotificationService.class);
        LibrarySystem library = new LibrarySystem(registry, notificationService);

        Optional<BookCheckout> firstCheckout = library.checkOutBook(bookId, userId1);
        assertTrue(firstCheckout.isPresent(), "First checkout should succeed");
        verify(notificationService).bookCheckedOut(bookId, userId1);
        verify(notificationService, never()).bookCheckoutFailed(any(), any());

        Optional<BookCheckout> secondCheckout = library.checkOutBook(bookId, userId2);
        assertFalse(secondCheckout.isPresent(), "Second checkout should fail");
        verify(notificationService).bookCheckoutFailed(bookId, userId2);
        verify(notificationService, times(1)).bookCheckedOut(bookId, userId1);
    }

    @Test
    void testReturnNotCheckedOutBook() {
        BookId bookId = new BookId("BK003");
        UserId userId = new UserId("USR003");
        BookRegistry registry = new InMemoryBookRegistry();
        NotificationService notificationService = mock(NotificationService.class);
        LibrarySystem library = new LibrarySystem(registry, notificationService);

        BookCheckout fakeCheckout = new BookCheckout(bookId, userId);
        boolean returned = library.returnBook(fakeCheckout);
        assertFalse(returned, "Returning a book that was not checked out should fail");
        verify(notificationService).bookReturnFailed(bookId);
        verify(notificationService, never()).bookReturned(any());
    }
}
