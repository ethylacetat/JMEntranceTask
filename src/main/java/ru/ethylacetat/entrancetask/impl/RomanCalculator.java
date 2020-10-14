package ru.ethylacetat.entrancetask.impl;

import ru.ethylacetat.entrancetask.interfaces.Calculator;
import ru.ethylacetat.entrancetask.interfaces.NumberFactory;

public class RomanCalculator implements Calculator {

    private final Calculator delegateCalculator = new ArabicIntegerCalculator();
    private final NumberFactory numberFactory;

    public RomanCalculator(NumberFactory numberFactory) {
        this.numberFactory = numberFactory;
    }

    @Override
    public Number add(Number one, Number two) {
        return numberFactory.getNumber(delegateCalculator.add(one, two).intValue());
    }

    @Override
    public Number subtract(Number one, Number two) {
        return numberFactory.getNumber(delegateCalculator.subtract(one, two).intValue());
    }

    @Override
    public Number multiply(Number one, Number two) {
        return numberFactory.getNumber(delegateCalculator.multiply(one, two).intValue());
    }

    @Override
    public Number divide(Number one, Number two) {
        return numberFactory.getNumber(delegateCalculator.divide(one, two).intValue());
    }
}
