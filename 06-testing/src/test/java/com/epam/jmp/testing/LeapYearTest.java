package com.epam.jmp.testing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



class LeapYearTest {


    @Test
    public void isLeap_divisibleBy4NotBy100Or400_returnsTrue() {
        assertTrue(LeapYear.isLeap(4));
        assertTrue(LeapYear.isLeap(64));
        assertTrue(LeapYear.isLeap(2020));
        assertTrue(LeapYear.isLeap(2016));
    }

    @Test
    public void isLeap_divisibleBy4And100NotBy400_returnsFalse() {
        assertFalse(LeapYear.isLeap(100));
        assertFalse(LeapYear.isLeap(1900));
        assertFalse(LeapYear.isLeap(2100));
    }


    @Test
    public void isLeap_divisibleBy4And100And400_returnsTrue() {
        assertTrue(LeapYear.isLeap(400));
        assertTrue(LeapYear.isLeap(1600));
        assertTrue(LeapYear.isLeap(2000));
    }


    @Test
    public void isLeap_notDivisibleBy4_returnsFalse() {
        assertFalse(LeapYear.isLeap(1));
        assertFalse(LeapYear.isLeap(2));
        assertFalse(LeapYear.isLeap(3));
        assertFalse(LeapYear.isLeap(2019));
    }


    @Test
    public void isLeap_negativeValues() {
        assertFalse(LeapYear.isLeap(-1));
        assertFalse(LeapYear.isLeap(-2));
        assertFalse(LeapYear.isLeap(-3));
        assertTrue(LeapYear.isLeap(-4));
        assertFalse(LeapYear.isLeap(-100));
        assertTrue(LeapYear.isLeap(-400));
    }
}