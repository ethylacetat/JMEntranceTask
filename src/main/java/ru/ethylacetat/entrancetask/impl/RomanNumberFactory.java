package ru.ethylacetat.entrancetask.impl;

import ru.ethylacetat.entrancetask.RomanNumber;
import ru.ethylacetat.entrancetask.interfaces.NumberFactory;

import java.util.HashMap;
import java.util.Map;

public class RomanNumberFactory implements NumberFactory {

    private final int cacheValue;

    private final Number[] cachedNumbers;
    private final Map<String, RomanNumber> mapCachedNumbers = new HashMap<>();

    public RomanNumberFactory(int cacheValue){
        if(cacheValue <= 0){
            this.cacheValue = 0;
            cachedNumbers = new Number[0];
        } else {
            this.cacheValue = cacheValue;

            cachedNumbers = new Number[cacheValue];

            cachedNumbers[0] = null;
            for (int i = 1; i < cacheValue; i++) {
                RomanNumber num = new RomanNumber(i);

                cachedNumbers[i] = num;
                mapCachedNumbers.put(num.romanValue(), num);
            }
        }


    }

    @Override
    public Number getNumber(int number) {
        if(number < cacheValue && number > 0){
            return cachedNumbers[number];
        }
        return new RomanNumber(number);
    }

    @Override
    public Number getNumber(String romanNumber) {
        if(cacheValue > 0){
            RomanNumber number = mapCachedNumbers.get(romanNumber);
            if(number != null){
                return number;
            }
        }
        return new RomanNumber(romanNumber);
    }
}
