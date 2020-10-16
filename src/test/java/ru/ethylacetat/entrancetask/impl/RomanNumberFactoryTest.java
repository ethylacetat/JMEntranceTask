package ru.ethylacetat.entrancetask.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

class RomanNumberFactoryTest {

    @DisplayName("Test roman number caching")
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @CsvSource({
            "1", "10"
    })
    void testNumberCache(int cacheNumberAmount){
        RomanNumberFactory romanNumberFactory = new RomanNumberFactory(cacheNumberAmount);
        for (int i = 1; i < cacheNumberAmount; i++) {
            assertSame(romanNumberFactory.getNumber(i), romanNumberFactory.getNumber(i));
        }
    }

    @DisplayName("Test out of cache roman number caching")
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @CsvSource({
            "1", "10"
    })
    void testOutOfCache(int cacheNumberAmount){
        RomanNumberFactory romanNumberFactory = new RomanNumberFactory(cacheNumberAmount);

        assertNotSame(
                romanNumberFactory.getNumber(cacheNumberAmount+ 1), romanNumberFactory.getNumber(cacheNumberAmount + 1)
        );

    }

    @DisplayName("Test woth no cache roman number")
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @CsvSource({
            "0"
    })
    void testNoCache(int cacheNumberAmount){
        RomanNumberFactory romanNumberFactory = new RomanNumberFactory(cacheNumberAmount);

        assertNotSame(
                romanNumberFactory.getNumber(10), romanNumberFactory.getNumber(10)
        );

    }

}