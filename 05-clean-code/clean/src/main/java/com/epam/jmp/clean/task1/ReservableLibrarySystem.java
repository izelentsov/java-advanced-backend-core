package com.epam.jmp.clean.task1;

import java.util.Optional;

import com.epam.jmp.clean.task1.model.BookCheckout;
import com.epam.jmp.clean.task1.model.BookId;
import com.epam.jmp.clean.task1.model.UserId;
import com.epam.jmp.clean.task1.repository.BookRegistry;
import com.epam.jmp.clean.task1.repository.WaitingListRegistry;
import com.epam.jmp.clean.task1.service.NotificationService;
import com.epam.jmp.clean.task1.service.ReservationNotificationService;


public class ReservableLibrarySystem extends LibrarySystem {
    private final WaitingListRegistry waitingList;
    private final ReservationNotificationService reservationNotificationService;

    public ReservableLibrarySystem(BookRegistry bookRegistry,
                                   NotificationService notificationService,
                                   WaitingListRegistry waitingList,
                                   ReservationNotificationService reservationNotificationService) {
        super(bookRegistry, notificationService);
        this.waitingList = waitingList;
        this.reservationNotificationService = reservationNotificationService;
    }

    @Override
    public Optional<BookCheckout> checkOutBook(BookId bookId, UserId userId) {
        if (isBookCheckedOut(bookId)) {
            return handleCheckedOutBook(bookId, userId);
        } else {
            return handleAvailableBook(bookId, userId);
        }
    }

    private boolean isBookCheckedOut(BookId bookId) {
        return super.bookRegistry.isBookCheckedOut(bookId);
    }

    private Optional<BookCheckout> handleCheckedOutBook(BookId bookId, UserId userId) {
        if (!waitingList.isUserInWaitingList(bookId, userId)) {
            waitingList.addToWaitingList(bookId, userId);
            reservationNotificationService.userAddedToWaitingList(bookId, userId);
        }
        super.notificationService.bookCheckoutFailed(bookId, userId);
        return Optional.empty();
    }

    private Optional<BookCheckout> handleAvailableBook(BookId bookId, UserId userId) {
        Optional<UserId> firstInLine = waitingList.peekNextInLine(bookId);
        if (firstInLine.isPresent()) {
            if (firstInLine.get().equals(userId)) {
                return checkoutForFirstInWaitingList(bookId, userId);
            } else {
                super.notificationService.bookCheckoutFailed(bookId, userId);
                return Optional.empty();
            }
        } else {
            return super.checkOutBook(bookId, userId);
        }
    }

    private Optional<BookCheckout> checkoutForFirstInWaitingList(BookId bookId, UserId userId) {
        waitingList.popNextInLine(bookId);
        reservationNotificationService.userRemovedFromWaitingList(bookId, userId);
        return super.checkOutBook(bookId, userId);
    }

    @Override
    public boolean returnBook(BookCheckout checkout) {
        boolean wasCheckedOut = isBookCheckedOut(checkout.bookId());
        boolean result = super.returnBook(checkout);
        if (wasCheckedOut) {
            // Notify next user in waiting list if any
            Optional<UserId> nextUser = waitingList.peekNextInLine(checkout.bookId());
            nextUser.ifPresent(userId -> reservationNotificationService.userCanNowCheckOut(checkout.bookId(), userId));
        }
        return result;
    }
}
