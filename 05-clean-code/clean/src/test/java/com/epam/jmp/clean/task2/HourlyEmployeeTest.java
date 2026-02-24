package com.epam.jmp.clean.task2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



class HourlyEmployeeTest {

    @Test
    void shouldCalculatePayCorrectly() {
        // Given: hourly employee with $25/hour rate working 160 hours
        HourlyEmployee employee = new HourlyEmployee(
            "Sarah Connor",
            new Money(25),
            160,
            0.10
        );

        // When: calculating pay
        Money pay = assertDoesNotThrow(employee::calculatePay);

        // Then: pay should be hourly rate * hours worked (25 * 160 = 4000)
        assertEquals(4000.0, pay.amount(), 0.01);
    }

    @Test
    void shouldCalculateBonusCorrectly() {
        // Given: hourly employee with $30/hour, 150 hours, and 12% bonus rate
        HourlyEmployee employee = new HourlyEmployee(
            "Kyle Reese",
            new Money(30),
            150,
            0.12
        );

        // When: calculating bonus
        Money bonus = assertDoesNotThrow(employee::calculateBonus);

        // Then: bonus should be total pay * bonus rate (30 * 150 * 0.12 = 540)
        assertEquals(540.0, bonus.amount(), 0.01);
    }

}

