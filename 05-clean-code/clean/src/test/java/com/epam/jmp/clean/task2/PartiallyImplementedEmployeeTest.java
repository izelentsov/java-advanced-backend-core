package com.epam.jmp.clean.task2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Direct subclass of Employee that implements one operation
 * without exception, and throws on the other operation.
 */
class PartiallyImplementedEmployeeTest {

    /**
     * Test employee that only supports calculatePay() but not calculateBonus()
     */
    static class PayOnlyEmployee extends Employee {
        private static final String TYPE = "PAY_ONLY";
        private final Money fixedPay;

        public PayOnlyEmployee(String name, Money fixedPay) {
            super(name, TYPE);
            this.fixedPay = fixedPay;
        }

        @Override
        public Money calculatePay() {
            return fixedPay;
        }

        // calculateBonus() is not overridden - will throw InvalidEmployeeType
    }

    /**
     * Test employee that only supports calculateBonus() but not calculatePay()
     */
    static class BonusOnlyEmployee extends Employee {
        private static final String TYPE = "BONUS_ONLY";
        private final Money fixedBonus;

        public BonusOnlyEmployee(String name, Money fixedBonus) {
            super(name, TYPE);
            this.fixedBonus = fixedBonus;
        }

        @Override
        public Money calculateBonus() {
            return fixedBonus;
        }

        // calculatePay() is not overridden - will throw InvalidEmployeeType
    }

    @Test
    void partialImplementation_demonstratesLspCompliance() {
        // Given: polymorphic collection of employees with partial implementations
        Employee payOnlyEmp = new PayOnlyEmployee("Worker 1", new Money(3000));
        Employee bonusOnlyEmp = new BonusOnlyEmployee("Worker 2", new Money(500));

        // When/Then: clients must handle exceptions as per the base class contract
        // This demonstrates LSP compliance - the contract forces proper error handling

        // Pay calculation might throw
        assertDoesNotThrow(payOnlyEmp::calculatePay);
        assertThrows(InvalidEmployeeType.class, bonusOnlyEmp::calculatePay);

        // Bonus calculation might throw
        assertThrows(InvalidEmployeeType.class, payOnlyEmp::calculateBonus);
        assertDoesNotThrow(bonusOnlyEmp::calculateBonus);
    }
}

