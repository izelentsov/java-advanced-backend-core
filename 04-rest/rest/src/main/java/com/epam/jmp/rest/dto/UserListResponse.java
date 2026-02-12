package com.epam.jmp.rest.dto;


import java.time.Instant;
import java.util.List;

import com.epam.jmp.rest.entity.User;



public record UserListResponse(List<User> users, Instant timestamp) {
}
