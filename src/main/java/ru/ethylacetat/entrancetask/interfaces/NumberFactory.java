package ru.ethylacetat.entrancetask.interfaces;

public interface NumberFactory {

    Number getNumber(int number);

    Number getNumber(String number);

    default Number[] getNumber(String... numbers) {
        Number[] arr = new Number[numbers.length];
        for (int i = 0; i < arr.length; i++) {
            // TODO: Try && reThrow
            arr[i] = getNumber(numbers[i]);
        }
        return arr;
    }

    default Number[] getNumber(int... numbers) {
        Number[] arr = new Number[numbers.length];
        for (int i = 0; i < arr.length; i++) {
            // TODO: Try && reThrow
            arr[i] = getNumber(numbers[i]);
        }
        return arr;
    }
}
