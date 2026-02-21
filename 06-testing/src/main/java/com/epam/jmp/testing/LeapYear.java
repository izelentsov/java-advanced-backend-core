package com.epam.jmp.testing;



public class LeapYear {

    public static boolean isLeap(int year) {
        if (year < 0) {
            return isLeap(-year);
        }
        if (year % 4 != 0) {
            return false;
        } else if (year % 100 != 0) {
            return true;
        } else {
            return year % 400 == 0;
        }
    }

}
