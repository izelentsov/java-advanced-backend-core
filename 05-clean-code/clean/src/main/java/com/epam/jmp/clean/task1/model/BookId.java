package com.epam.jmp.clean.task1.model;

public record BookId(String value) {
    public BookId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("BookId cannot be null or blank");
        }
    }
    @Override
    public String toString() {
        return value;
    }
}

