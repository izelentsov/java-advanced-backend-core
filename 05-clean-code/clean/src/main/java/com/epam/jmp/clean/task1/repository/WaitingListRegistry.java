package com.epam.jmp.clean.task1.repository;

import com.epam.jmp.clean.task1.model.BookId;
import com.epam.jmp.clean.task1.model.UserId;
import java.util.List;
import java.util.Optional;

public interface WaitingListRegistry {
    void addToWaitingList(BookId bookId, UserId userId);
    Optional<UserId> popNextInLine(BookId bookId);
    Optional<UserId> peekNextInLine(BookId bookId);
    boolean isUserInWaitingList(BookId bookId, UserId userId);
}

