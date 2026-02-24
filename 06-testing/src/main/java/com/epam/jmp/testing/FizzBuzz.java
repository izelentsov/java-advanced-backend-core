package com.epam.jmp.testing;



public class FizzBuzz {


    public static String fizzBuzz(int i) {
        boolean divisibleBy3 = (i % 3 == 0);
        boolean divisibleBy5 = (i % 5 == 0);
        if (divisibleBy3 && divisibleBy5) {
            return "FizzBuzz";
        } else if (divisibleBy3) {
            return "Fizz";
        } else if (divisibleBy5) {
            return "Buzz";
        } else {
            return String.valueOf(i);
        }
    }

}
