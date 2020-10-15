package ru.ethylacetat.entrancetask.impl;

import ru.ethylacetat.entrancetask.interfaces.NumberFactory;

public class ArabicIntegerFactory implements NumberFactory {

    @Override
    public Number getNumber(int number) {
        return number;
    }

    @Override
    public Number getNumber(String number) {
        return Integer.parseInt(number);
    }
}
