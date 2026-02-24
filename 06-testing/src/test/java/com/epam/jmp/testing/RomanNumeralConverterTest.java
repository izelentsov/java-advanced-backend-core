package com.epam.jmp.testing;


import org.junit.jupiter.api.Test;
import static com.epam.jmp.testing.RomanNumeralConverter.toRoman;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class RomanNumeralConverterTest {


    @Test
    public void toRoman_zeroOrNegative_throws() {
        assertThatThrownBy(() -> toRoman(0))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> toRoman(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    public void toRoman_oneToTen_romanBasicNumeral() {
        assertThat(toRoman(1)).isEqualTo("I");
        assertThat(toRoman(2)).isEqualTo("II");
        assertThat(toRoman(3)).isEqualTo("III");
        assertThat(toRoman(4)).isEqualTo("IV");
        assertThat(toRoman(5)).isEqualTo("V");
        assertThat(toRoman(6)).isEqualTo("VI");
        assertThat(toRoman(7)).isEqualTo("VII");
        assertThat(toRoman(8)).isEqualTo("VIII");
        assertThat(toRoman(9)).isEqualTo("IX");
        assertThat(toRoman(10)).isEqualTo("X");

    }


    @Test
    public void toRoman_tenToTwenty_romanNumeral() {
        assertThat(toRoman(11)).isEqualTo("XI");
        assertThat(toRoman(12)).isEqualTo("XII");
        assertThat(toRoman(13)).isEqualTo("XIII");
        assertThat(toRoman(14)).isEqualTo("XIV");
        assertThat(toRoman(15)).isEqualTo("XV");
        assertThat(toRoman(16)).isEqualTo("XVI");
        assertThat(toRoman(17)).isEqualTo("XVII");
        assertThat(toRoman(18)).isEqualTo("XVIII");
        assertThat(toRoman(19)).isEqualTo("XIX");
        assertThat(toRoman(20)).isEqualTo("XX");
    }


    @Test
    public void toRoman_aroundFifty_romanNumeral() {
        assertThat(toRoman(40)).isEqualTo("XL");
        assertThat(toRoman(45)).isEqualTo("XLV");
        assertThat(toRoman(49)).isEqualTo("XLIX");
        assertThat(toRoman(50)).isEqualTo("L");
    }


    @Test
    public void toRoman_aroundOneHundred_romanNumeral() {
        assertThat(toRoman(90)).isEqualTo("XC");
        assertThat(toRoman(95)).isEqualTo("XCV");
        assertThat(toRoman(99)).isEqualTo("XCIX");
        assertThat(toRoman(100)).isEqualTo("C");
    }


    @Test
    public void toRoman_aroundFiveHundred_romanNumeral() {
        assertThat(toRoman(400)).isEqualTo("CD");
        assertThat(toRoman(450)).isEqualTo("CDL");
        assertThat(toRoman(499)).isEqualTo("CDXCIX");
        assertThat(toRoman(500)).isEqualTo("D");
    }


    @Test
    public void toRoman_aroundOneThousand_romanNumeral() {
        assertThat(toRoman(900)).isEqualTo("CM");
        assertThat(toRoman(950)).isEqualTo("CML");
        assertThat(toRoman(999)).isEqualTo("CMXCIX");
        assertThat(toRoman(1000)).isEqualTo("M");
    }

    }