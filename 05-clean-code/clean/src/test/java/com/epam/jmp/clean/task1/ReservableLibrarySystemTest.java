package com.epam.jmp.clean.task1;

import org.junit.jupiter.api.Test;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



class ReservableLibrarySystemTest {
    @Test
    void testReservationAndWaitingListBehavior() {
        BookId bookId = new BookId("BK100");
        UserId user1 = new UserId("USR1");
        UserId user2 = new UserId("USR2");
        UserId user3 = new UserId("USR3");
        BookRegistry registry = new InMemoryBookRegistry();
        BookWaitingList waitingList = new BookWaitingList();
        NotificationService notificationService = mock(NotificationService.class);
        ReservationNotificationService reservationNotificationService = mock(ReservationNotificationService.class);
        ReservableLibrarySystem library = new ReservableLibrarySystem(registry, notificationService, waitingList, reservationNotificationService);

        // User 1 checks out the book
        Optional<BookCheckout> checkout1 = library.checkOutBook(bookId, user1);
        assertTrue(checkout1.isPresent());
        verify(notificationService).bookCheckedOut(bookId, user1);
        verify(notificationService, never()).bookCheckoutFailed(any(), any());

        // User 2 tries to check out, should be added to waiting list
        Optional<BookCheckout> checkout2 = library.checkOutBook(bookId, user2);
        assertFalse(checkout2.isPresent());
        verify(notificationService).bookCheckoutFailed(bookId, user2);
        verify(reservationNotificationService).userAddedToWaitingList(bookId, user2);

        // User 3 tries to check out, should be added to waiting list
        Optional<BookCheckout> checkout3 = library.checkOutBook(bookId, user3);
        assertFalse(checkout3.isPresent());
        verify(notificationService).bookCheckoutFailed(bookId, user3);
        verify(reservationNotificationService).userAddedToWaitingList(bookId, user3);

        // User 2 tries to check out again (already in waiting list), should not be added again
        library.checkOutBook(bookId, user2);
        verify(reservationNotificationService, times(1)).userAddedToWaitingList(bookId, user2);

        // User 1 returns the book, user 2 should be notified
        assertTrue(library.returnBook(new BookCheckout(bookId, user1)));
        verify(notificationService).bookReturned(bookId);
        verify(reservationNotificationService).userCanNowCheckOut(bookId, user2);

        // User 2 (first in waiting list) checks out the book
        Optional<BookCheckout> checkout2b = library.checkOutBook(bookId, user2);
        assertTrue(checkout2b.isPresent());
        verify(notificationService, times(1)).bookCheckedOut(bookId, user2);
        verify(reservationNotificationService).userRemovedFromWaitingList(bookId, user2);

        // User 3 (now first in waiting list) tries to check out, should succeed
        Optional<BookCheckout> checkout3b = library.checkOutBook(bookId, user3);
        assertFalse(checkout3b.isPresent()); // Book is checked out by user2
        verify(notificationService, times(2)).bookCheckoutFailed(bookId, user3);
    }
}

