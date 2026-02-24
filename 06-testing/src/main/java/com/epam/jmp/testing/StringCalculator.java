package com.epam.jmp.testing;



public class StringCalculator {

    public static int add(String numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        if (numbers.isEmpty()) {
            return 0;
        }
        String[] parts = numbers.split(",");
        int sum = 0;
        for (String part : parts) {
            sum += Integer.parseInt(part);
        }
        return sum;
    }


}
