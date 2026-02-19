package com.epam.jmp.clean.task2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



class SalariedEmployeeTest {

    @Test
    void shouldCalculatePayCorrectly() {
        // Given: salaried employee with $6000 monthly salary
        SalariedEmployee employee = new SalariedEmployee(
            "Michael Scott",
            new Money(6000),
            0.10
        );

        // When: calculating pay
        Money pay = assertDoesNotThrow(employee::calculatePay);

        // Then: pay should equal the salary
        assertEquals(6000.0, pay.amount(), 0.01);
    }


    @Test
    void shouldCalculateBonusCorrectly() {
        // Given: salaried employee with $6000 salary and 10% bonus rate
        SalariedEmployee employee = new SalariedEmployee(
            "Pam Beesly",
            new Money(6000),
            0.10
        );

        // When: calculating bonus
        Money bonus = assertDoesNotThrow(employee::calculateBonus);

        // Then: bonus should be salary * bonus rate (6000 * 0.10 = 600)
        assertEquals(600.0, bonus.amount(), 0.01);
    }

}

