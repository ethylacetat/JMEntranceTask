package ru.ethylacetat.entrancetask.impl;

import ru.ethylacetat.entrancetask.interfaces.Calculator;
import ru.ethylacetat.entrancetask.interfaces.CalculatorFactory;
import ru.ethylacetat.entrancetask.interfaces.NumberFactory;

public class AbstractNumberFactory implements CalculatorFactory {

    NumberFactory arabicFactory = new ArabicIntegerFactory();
    NumberFactory romanFactory = new RomanNumberFactory();

    Calculator arabicCalculator = new ArabicIntegerCalculator();
    Calculator romanCalculator = new RomanCalculator(romanFactory);

    public NumberFactory getNumberFactory(String numberType) {
        switch (numberType.toLowerCase()) {
            case "arabic":
                return arabicFactory;
            case "roman":
                return romanFactory;
            default:
                throw new RuntimeException(numberType + " type not supported");
        }
    }

    @Override
    public Calculator getCalculator(String numberType) {
        switch (numberType.toLowerCase()) {
            case "arabic":
                return arabicCalculator;
            case "roman":
                return romanCalculator;
            default:
                throw new RuntimeException(numberType + " type not supported");
        }
    }
}
