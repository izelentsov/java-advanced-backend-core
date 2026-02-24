package com.epam.jmp.testing;


import org.junit.jupiter.api.Test;
import static com.epam.jmp.testing.PasswordValidator.validate;
import static org.assertj.core.api.Assertions.assertThat;


class PasswordValidatorTest {


    @Test
    public void validate_lessThan8Chars_returnsFalse() {
        assertThat(validate("")).isFalse();
        assertThat(validate("Abc1")).isFalse();
        assertThat(validate("Abc4567")).isFalse();
    }


    @Test
    public void validate_noUpperCase_returnsFalse() {
        assertThat(validate("abcdefg1")).isFalse();
        assertThat(validate("12345678")).isFalse();
    }


    @Test
    public void validate_noLowerCase_returnsFalse() {
        assertThat(validate("ABCDEFG1")).isFalse();
        assertThat(validate("12345678")).isFalse();
    }


    @Test
    public void validate_noDigit_returnsFalse() {
        assertThat(validate("Abcdefgh")).isFalse();
        assertThat(validate("ABCDEFGH")).isFalse();
    }


    @Test
    public void validate_validPassword_returnsTrue() {
        assertThat(validate("Abcdefg1")).isTrue();
        assertThat(validate("A1b2C3d4")).isTrue();
    }
}