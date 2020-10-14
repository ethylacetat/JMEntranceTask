package ru.ethylacetat.entrancetask.impl;

import ru.ethylacetat.entrancetask.interfaces.Calculator;

public class ArabicIntegerCalculator implements Calculator {

    @Override
    public Number add(Number one, Number two) {
        return one.intValue() + two.intValue();
    }

    @Override
    public Number subtract(Number one, Number two) {
        return one.intValue() - two.intValue();
    }

    @Override
    public Number multiply(Number one, Number two) {
        return one.intValue() * two.intValue();
    }

    @Override
    public Number divide(Number one, Number two) {
        return one.intValue() / two.intValue();
    }
}
