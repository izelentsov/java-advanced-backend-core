package com.epam.jmp.clean.task1.repository;

import com.epam.jmp.clean.task1.model.BookId;
import com.epam.jmp.clean.task1.model.UserId;
import java.util.*;



public class InMemoryWaitingListRegistry implements WaitingListRegistry {
    private final Map<BookId, Deque<UserId>> waitingLists = new HashMap<>();

    @Override
    public void addToWaitingList(BookId bookId, UserId userId) {
        waitingLists.computeIfAbsent(bookId, k -> new ArrayDeque<>()).addLast(userId);
    }

    @Override
    public Optional<UserId> popNextInLine(BookId bookId) {
        Deque<UserId> queue = waitingLists.get(bookId);
        if (queue == null || queue.isEmpty()) {
            return Optional.empty();
        }
        UserId next = queue.pollFirst();
        if (queue.isEmpty()) {
            waitingLists.remove(bookId);
        }
        return Optional.of(next);
    }

    @Override
    public Optional<UserId> peekNextInLine(BookId bookId) {
        Deque<UserId> queue = waitingLists.get(bookId);
        return (queue == null || queue.isEmpty()) ? Optional.empty() : Optional.of(queue.peekFirst());
    }

    @Override
    public boolean isUserInWaitingList(BookId bookId, UserId userId) {
        Deque<UserId> queue = waitingLists.get(bookId);
        return queue != null && queue.contains(userId);
    }
}

