package ru.ethylacetat.entrancetask;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorApplicationTest {

    // Addition
    // ================================
    @DisplayName("Test application with addition valid arabic number")
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @CsvSource({
            "1+1, 2", "9+9, 18", "1+9, 10", "9+1, 10", "5+5, 10",
            "1+ 1, 2", "9 +9, 18", "1 + 9, 10", "10 + 9, 19", "10 + 10, 20"
    })
    void additionWithValidArabicArgs(String expression, String actual) throws Throwable {
        System.out.println(expression);
        test(expression + "\nexit", actual);
    }

    @DisplayName("Test application with addition valid roman number")
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @CsvSource({
            "I+I, II", "IX+IX, XVIII", "I+IX, X", "IX+I, X", "V+V, X",
            "I+ I, II", "IX +IX, XVIII", "I + IX, X", "X + IX, XIX", "IX +X, XIX"
    })
    void additionWithValidRomanArgs(String expression, String actual) throws Throwable {
        System.out.println(expression);
        test(expression + "\nexit", actual);
    }
    // ================================

    // Subtraction
    // ================================
    @DisplayName("Test application with subtraction valid arabic number")
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @CsvSource({
            "2-1, 1", "9-8, 1", "9-1, 8", "5-4, 1",
            "2- 1, 1", "9 -8, 1", "9 -1, 8", "8-8, 0", "10-10, 0", "10-8, 2"
    })
    void subtractionWithValidArabicArgs(String expression, String actual) throws Throwable {
        System.out.println(expression);
        test(expression + "\nexit", actual);
    }

    @DisplayName("Test application with subtraction valid roman number")
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @CsvSource({
            "II-I, I", "IX-VIII, I", "IX-I, VIII", "V-IV, I",
            "II- I, I", "IX -VIII, I", "IX -I, VIII", "X-IX, I", "X-VIII, II"
    })
    void subtractionWithValidRomanArgs(String expression, String actual) throws Throwable {
        System.out.println(expression);
        test(expression + "\nexit", actual);
    }
    // ================================


    // Multiplication
    // ================================
    @DisplayName("Test application with multiplication valid arabic number")
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @CsvSource({
            "1*1, 1", "9*9, 81", "9*1, 9", "1*9, 9", "5*5, 25",
            "2* 1, 2", "9 *8, 72", "9 *1, 9", "10 *1, 10", "10 *10, 100"
    })
    void multiplicationWithValidArabicArgs(String expression, String actual) throws Throwable {
        System.out.println(expression);
        test(expression + "\nexit", actual);
    }

    @DisplayName("Test application with multiplication valid roman number")
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @CsvSource({
            "I*I, I", "IX*IX, LXXXI", "IX*I, IX", "I*IX, IX", "V*V, XXV",
            "II* I, II", "IX *VIII, LXXII", "IX *II, XVIII", "X *I, X", "X *X, C"
    })
    void multiplicationWithValidRomanArgs(String expression, String actual) throws Throwable {
        System.out.println(expression);
        test(expression + "\nexit", actual);
    }
    // ================================


    // Division
    // ================================
    @DisplayName("Test application with division valid arabic number")
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @CsvSource({
            "1/1, 1", "9/9, 1", "9/1, 9", "1/9, 0", "5/3, 1",
            "6/ 2, 3", "6 /3, 2", "8 /2, 4", "10/5, 2", "10/10, 1"
    })
    void divisionWithValidArabicArgs(String expression, String actual) throws Throwable {
        System.out.println(expression);
        test(expression + "\nexit", actual);
    }

    @DisplayName("Test application with division valid roman number")
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @CsvSource({
            "I/I, I", "IX/IX, I", "IX/I, IX", "V/III, I",
            "VI/ II, III", "VI /III, II", "VIII /II, IV", "X/V, II", "X/X, I"
    })
    void divisionWithValidRomanArgs(String expression, String actual) throws Throwable {
        System.out.println(expression);
        test(expression + "\nexit", actual);
    }
    // ================================

    @DisplayName("Test application with no expression in")
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @CsvSource({
            "qwert", "q23w","5484++","++++",
    })
    void testWithNoExpressionArgs(String expression) throws Throwable {
        testWithException(expression + "\nexit", ArithmeticException.class);
    }

    @DisplayName("Test application with out of range number")
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @CsvSource({
            "0+0", "11+11","100+0","11*0","0/100"
    })
    void testWithOutOfRangeArgs(String expression) throws Throwable {
        testWithException(expression + "\nexit", ArithmeticException.class);
    }

    @DisplayName("Test application with different format number")
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @CsvSource({
            "1+I", "IX+1","V+5"
    })
    void testWithDifferentFormatArgs(String expression) throws Throwable {
        testWithException(expression + "\nexit", ArithmeticException.class);
    }

    @DisplayName("Test application with arithmetic error")
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @CsvSource({
            "1/0"
    })
    void testWithArithmeticError(String expression) throws Throwable {
        testWithException(expression + "\nexit", ArithmeticException.class);
    }

    @DisplayName("Test application with arithmetic error")
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @CsvSource({
            "I-I", "I/X"
    })
    void testWitWrongFormat(String expression) throws Throwable {
        testWithException(expression + "\nexit", NumberFormatException.class);
    }

    private void testWithException(String input, Class<? extends Exception> actualException) throws Throwable {
        try (ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes())) {
            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                System.setIn(in);
                System.setOut(new PrintStream(out));

                Assertions.assertThrows(actualException, () -> CalculatorApplication.main(new String[0]));
            }
        }
    }

    private void test(String input, String actual) throws Throwable {
        try (ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes())) {
            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                System.setIn(in);
                System.setOut(new PrintStream(out));

                CalculatorApplication.main(new String[0]);

                Assertions.assertEquals(out.toString().trim(), actual);
            }
        }
    }


}