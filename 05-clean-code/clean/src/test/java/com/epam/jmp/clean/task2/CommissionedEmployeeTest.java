package com.epam.jmp.clean.task2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



class CommissionedEmployeeTest {

    @Test
    void shouldCalculatePayCorrectly() {
        // Given: commissioned employee with $5000 salary and 10% commission rate
        CommissionedEmployee employee = new CommissionedEmployee(
            "John Doe",
            new Money(5000),
            0.10,
            0.15
        );

        // When: calculating pay
        Money pay = assertDoesNotThrow(employee::calculatePay);

        // Then: pay should be salary + commission (5000 * 1.10 = 5500)
        assertEquals(5500.0, pay.amount(), 0.01);
    }

    @Test
    void shouldCalculateBonusCorrectly() {
        // Given: commissioned employee with $5000 salary, 10% commission, and 15% bonus rate
        CommissionedEmployee employee = new CommissionedEmployee(
            "Jane Smith",
            new Money(5000),
            0.10,
            0.15
        );

        // When: calculating bonus
        Money bonus = assertDoesNotThrow(employee::calculateBonus);

        // Then: bonus should be commission earnings * bonus rate (5000 * 0.10 * 0.15 = 75)
        assertEquals(75.0, bonus.amount(), 0.01);
    }

}

