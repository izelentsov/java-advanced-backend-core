package com.epam.jmp.clean.task1.model;

public record UserId(String value) {
    public UserId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("UserId cannot be null or blank");
        }
    }
    @Override
    public String toString() {
        return value;
    }
}

