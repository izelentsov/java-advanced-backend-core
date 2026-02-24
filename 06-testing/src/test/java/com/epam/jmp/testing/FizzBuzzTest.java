package com.epam.jmp.testing;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;





class FizzBuzzTest {


    @Test
    public void fizzBuzz_divisibleBy3_returnsFizz() {
        assertThat(FizzBuzz.fizzBuzz(3)).isEqualTo("Fizz");
        assertThat(FizzBuzz.fizzBuzz(9)).isEqualTo("Fizz");
    }


    @Test
    public void fizzBuzz_divisibleBy5_returnsBuzz() {
        assertThat(FizzBuzz.fizzBuzz(5)).isEqualTo("Buzz");
        assertThat(FizzBuzz.fizzBuzz(10)).isEqualTo("Buzz");
    }


    @Test
    public void fizzBuzz_divisibleBy3And5_returnsFizzBuzz() {
        assertThat(FizzBuzz.fizzBuzz(15)).isEqualTo("FizzBuzz");
        assertThat(FizzBuzz.fizzBuzz(30)).isEqualTo("FizzBuzz");
    }


    @Test
    public void fizzBuzz_notDivisibleBy3Or5_returnsInput() {
        assertThat(FizzBuzz.fizzBuzz(1)).isEqualTo("1");
        assertThat(FizzBuzz.fizzBuzz(2)).isEqualTo("2");
    }

        @Test
        public void fizzBuzz_multipleEdgeCases() {
            assertThat(FizzBuzz.fizzBuzz(0)).isEqualTo("FizzBuzz");
            assertThat(FizzBuzz.fizzBuzz(45)).isEqualTo("FizzBuzz");
            assertThat(FizzBuzz.fizzBuzz(6)).isEqualTo("Fizz");
            assertThat(FizzBuzz.fizzBuzz(12)).isEqualTo("Fizz");
            assertThat(FizzBuzz.fizzBuzz(20)).isEqualTo("Buzz");
            assertThat(FizzBuzz.fizzBuzz(25)).isEqualTo("Buzz");
            assertThat(FizzBuzz.fizzBuzz(7)).isEqualTo("7");
            assertThat(FizzBuzz.fizzBuzz(100)).isEqualTo("Buzz");
        }

        @Test
        public void fizzBuzz_negativeNumbers() {
            assertThat(FizzBuzz.fizzBuzz(-1)).isEqualTo("-1");
            assertThat(FizzBuzz.fizzBuzz(-3)).isEqualTo("Fizz");
            assertThat(FizzBuzz.fizzBuzz(-5)).isEqualTo("Buzz");
            assertThat(FizzBuzz.fizzBuzz(-15)).isEqualTo("FizzBuzz");
        }

}