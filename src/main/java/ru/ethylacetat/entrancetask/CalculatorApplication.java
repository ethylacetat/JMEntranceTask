package ru.ethylacetat.entrancetask;

import ru.ethylacetat.entrancetask.impl.AbstractNumberFactory;
import ru.ethylacetat.entrancetask.interfaces.Calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Pattern;

public class CalculatorApplication {

    private static final Pattern operatorsPattern = Pattern.compile("[+*\\-/]");

    private final AbstractNumberFactory abstractFactory = new AbstractNumberFactory();

    public static void main(String[] args) {
        new CalculatorApplication().run();
    }


    public void run(){
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            try {
               String in = buffReader.readLine();

               if(in.equalsIgnoreCase("exit")){
                   break;
               }

               in = in.replace(" ", "");
               System.out.println(calculate(in));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    public Number calculate(String expression) {
        String[] expArr = operatorsPattern.split(expression);

        if (expArr.length != 2) {
            throw new ArithmeticException("Expression must contain two numbers");
        }
        String factoryType = "roman";

        if (expArr[0].matches("[01-9]+")) {
            factoryType = "arabic";
        }

        Number[] nums;
        try {
            nums = abstractFactory.getNumberFactory(factoryType).getNumber(expArr[0], expArr[1]);
        } catch (NumberFormatException ex) {
            ArithmeticException exception = new ArithmeticException(
                    "Numbers must have the same format: " + Arrays.toString(expArr));
            exception.addSuppressed(ex);
            throw exception;
        }

        int one = nums[0].intValue();
        int two = nums[1].intValue();

        String operation = expression.substring(expArr[0].length(), expArr[0].length() + 1);

        if (one > 0 && one < 11 && two > 0 && two < 11) {
            Calculator calc = abstractFactory.getCalculator(factoryType);
            switch (operation) {
                case "+":
                    return calc.add(nums[0], nums[1]);
                case "-":
                    return calc.subtract(nums[0], nums[1]);
                case "*":
                    return calc.multiply(nums[0], nums[1]);
                case "/":
                    return calc.divide(nums[0], nums[1]);
                default:
                    throw new RuntimeException("Something wrong!");
            }
        } else {
            throw new ArithmeticException(
                    String.format("The numbers must be in range [%s, %s], but has %s and %s",
                            abstractFactory.getNumberFactory(factoryType).getNumber(1),
                            abstractFactory.getNumberFactory(factoryType).getNumber(9),
                            nums[0],
                            nums[1]
                    ));

        }

    }



}
