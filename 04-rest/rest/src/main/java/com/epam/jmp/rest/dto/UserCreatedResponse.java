package com.epam.jmp.rest.dto;


import java.time.Instant;



public record UserCreatedResponse(long id, Instant timestamp) {
}
