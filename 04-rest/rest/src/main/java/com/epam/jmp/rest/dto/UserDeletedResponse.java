package com.epam.jmp.rest.dto;


import java.time.Instant;



public record UserDeletedResponse(long id, Instant timestamp) {
}
