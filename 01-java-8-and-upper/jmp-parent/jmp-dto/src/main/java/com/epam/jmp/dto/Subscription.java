package com.epam.jmp.dto;

import java.time.LocalDate;


public record Subscription(CardNumber bankcard, LocalDate startDate) {

}
