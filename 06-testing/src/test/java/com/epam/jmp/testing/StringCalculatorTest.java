package com.epam.jmp.testing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



class StringCalculatorTest {


    @Test
    public void add_emptyString_returns0() {
        assertEquals(0, StringCalculator.add(""));
    }


    @Test
    public void add_singleNumber_returnsInput() {
        assertEquals(1, StringCalculator.add("1"));
        assertEquals(42, StringCalculator.add("42"));
    }

    @Test
    public void add_twoNumbersSeparatedByComma_returnsSum() {
        assertEquals(3, StringCalculator.add("1,2"));
        assertEquals(10, StringCalculator.add("4,6"));
    }

    @Test
    public void add_multipleNumbersSeparatedByComma_returnsSum() {
        assertEquals(6, StringCalculator.add("1,2,3"));
        assertEquals(15, StringCalculator.add("4,5,6"));
    }


    @Test
    public void add_multipleNumbersWithNegatives_returnsSum() {
        assertEquals(0, StringCalculator.add("-1,1"));
        assertEquals(-5, StringCalculator.add("-2,-3"));
        assertEquals(10, StringCalculator.add("4,-1,7"));
    }


    @Test
    public void add_nonNumericCharacters_throws() {
        assertThrows(NumberFormatException.class, () -> StringCalculator.add("1,a"));
        assertThrows(NumberFormatException.class, () -> StringCalculator.add("b,2"));
        assertThrows(NumberFormatException.class, () -> StringCalculator.add("3,c,4"));
        assertThrows(NumberFormatException.class, () -> StringCalculator.add("3a3"));
    }


    @Test
    public void add_illegalSeparator_throws() {
        assertThrows(NumberFormatException.class, () -> StringCalculator.add("1;2"));
        assertThrows(NumberFormatException.class, () -> StringCalculator.add("3|4,5"));
        assertThrows(NumberFormatException.class, () -> StringCalculator.add("5 6"));
    }


    @Test
    public void add_nullInput_throws() {
        assertThrows(IllegalArgumentException.class, () -> StringCalculator.add(null));
    }

}