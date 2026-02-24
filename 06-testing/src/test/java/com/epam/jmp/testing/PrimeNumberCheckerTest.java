package com.epam.jmp.testing;


import org.junit.jupiter.api.Test;
import static com.epam.jmp.testing.PrimeNumberChecker.isPrime;
import static org.assertj.core.api.Assertions.assertThat;




class PrimeNumberCheckerTest {


    @Test
    public void isPrime_notGreaterThan1_returnsFalse() {
        assertThat(isPrime(0)).isFalse();
        assertThat(isPrime(1)).isFalse();
        assertThat(isPrime(-1)).isFalse();
    }


    @Test
    public void isPrime_primeNumber_returnsTrue() {
        assertThat(isPrime(2)).isTrue();
        assertThat(isPrime(3)).isTrue();
        assertThat(isPrime(5)).isTrue();
        assertThat(isPrime(7)).isTrue();
    }


    @Test
    public void isPrime_nonPrimeNumber_returnsFalse() {
        assertThat(isPrime(4)).isFalse();
        assertThat(isPrime(6)).isFalse();
        assertThat(isPrime(8)).isFalse();
        assertThat(isPrime(9)).isFalse();
    }

}