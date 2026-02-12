package com.epam.jmp.rest.dto;


import java.time.Instant;



public record UserErrorResponse(Long userId, String errorMessage, Instant timestamp) {

    public UserErrorResponse(String errorMessage, Instant timestamp) {
        this(null, errorMessage, timestamp);
    }
}
