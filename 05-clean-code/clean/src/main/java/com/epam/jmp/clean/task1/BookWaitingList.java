package com.epam.jmp.clean.task1;

import java.util.*;

/**
 * Manages waiting lists for books. Each book can have a queue of users waiting for it.
 */
public class BookWaitingList {
    private final Map<BookId, Deque<UserId>> waitingLists = new HashMap<>();

    public void addToWaitingList(BookId bookId, UserId userId) {
        waitingLists.computeIfAbsent(bookId, k -> new ArrayDeque<>()).addLast(userId);
    }

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

    public Optional<UserId> peekNextInLine(BookId bookId) {
        Deque<UserId> queue = waitingLists.get(bookId);
        return (queue == null || queue.isEmpty()) ? Optional.empty() : Optional.of(queue.peekFirst());
    }

    public boolean isUserInWaitingList(BookId bookId, UserId userId) {
        Deque<UserId> queue = waitingLists.get(bookId);
        return queue != null && queue.contains(userId);
    }
}

