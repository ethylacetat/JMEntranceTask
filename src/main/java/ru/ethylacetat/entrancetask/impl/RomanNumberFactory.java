package ru.ethylacetat.entrancetask.impl;

import ru.ethylacetat.entrancetask.RomanNumber;
import ru.ethylacetat.entrancetask.interfaces.NumberFactory;

public class RomanNumberFactory implements NumberFactory {

    // TODO: cache value

    @Override
    public Number getNumber(int number) {
        return new RomanNumber(number);
    }

    @Override
    public Number getNumber(String number) {
        return new RomanNumber(number);
    }
}
