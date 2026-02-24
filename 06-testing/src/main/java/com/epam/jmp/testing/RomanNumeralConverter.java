package com.epam.jmp.testing;




public class RomanNumeralConverter {

    public static String toRoman(int i) {
        if (i <= 0 || i > 3999) {
            throw new IllegalArgumentException("Input must be between 1 and 3999");
        }
        StringBuilder sb = new StringBuilder();
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] numerals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int j = 0; j < values.length; j++) {
            while (i >= values[j]) {
                sb.append(numerals[j]);
                i -= values[j];
            }
        }
        return sb.toString();
    }

}
