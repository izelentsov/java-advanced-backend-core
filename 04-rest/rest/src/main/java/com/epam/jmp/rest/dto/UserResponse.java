package com.epam.jmp.rest.dto;


import java.time.Instant;

import com.epam.jmp.rest.entity.User;



public record UserResponse(User user, Instant timestamp) {
}
