package com.epam.jmp.clean.task2;




public record Money(double amount) {

    public Money multiply(double multiplier) {
        return new Money(amount * multiplier);
    }
}
