package com.epam.jmp.rest.dto;


import java.time.Instant;



public record UserUpdatedResponse(long id, Instant timestamp) {
}
