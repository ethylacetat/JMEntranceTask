package ru.ethylacetat.entrancetask;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RomanNumberTest {

    @DisplayName("Test parseToRoman method with valid args")
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @CsvSource({
            "1, I", "2, II", "3, III", "4, IV", "5, V", "6, VI", "7, VII", "8, VIII", "9, IX",
            "10, X", "20, XX", "30, XXX", "40, XL", "50, L", "60, LX", "70, LXX", "80, LXXX", "90, XC",
            "100, C", "200, CC", "300, CCC", "400, CD", "500, D", "600, DC", "700, DCC", "800, DCCC", "900, CM",
            "1000, M", "2000, MM", "3000, MMM",
            "12, XII", "381, CCCLXXXI", "371, CCCLXXI", "3999, MMMCMXCIX", "1234, MCCXXXIV", "444, CDXLIV",
    })
    void parseToRomanWithValidArgs(int arabicRepresentation, String actualRomanRepresentation) {
        Assertions.assertEquals(RomanNumber.parseToRoman(arabicRepresentation), actualRomanRepresentation);
    }

    @DisplayName("Test parseToArabic method with valid args")
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @CsvSource({
            "I, 1", "II , 2", "III, 3", "IV,4", "V,5", "VI,6", "VII,7", "VIII,8", "IX,9",
            "X,10", "XX,20", "XXX,30", "XL,40", "L,50", "LX,60", "LXX,70", "LXXX,80", "XC,90",
            "C,100", "CC,200", "CCC,300", "CD,400", "D,500", "DC,600", "DCC,700", "DCCC,800", "CM,900",
            "M,1000", "MM,2000", "MMM,3000",
            "XII,12", "CCCLXXXI,381", "CCCLXXI,371", "MMMCMXCIX,3999", "MCCXXXIV,1234", "CDXLIV,444",
    })
    void parseToArabicWithValidArgs(String roman, int actualArabic) {
        Assertions.assertEquals(RomanNumber.parseToArabic(roman), actualArabic);
    }

    // TODO: Test parse with invalid
    // TODO: Test hashcode/equals contract
}