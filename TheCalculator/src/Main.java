import java.util.Arrays;
import java.util.Scanner;

/*
Author: Alexey Chervenko
 */

public class Main {
    public static void main(String[] args) {
        String[] expression;

        while (true) {
            Scanner inputExpression = new Scanner(System.in);
            System.out.println("Введите выражение или введите Exit: ");
            expression = inputExpression.nextLine().split(" ");
            System.out.println(calc(expression));
        }
    }

    public static int calculateNumbers(int firstNumber, int secondNumber, String operator) {
        int result = 0;

        if (firstNumber > 10 || secondNumber > 10) {
            throw new RuntimeException("Числа могут быть от 1 до 10 включительно");
        }

        switch (operator) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "*":
                result = firstNumber * secondNumber;
                break;
            case "/":
                result = firstNumber / secondNumber;
                break;
        }
        return result;
    }

    public static String calc(String[] expression) {
        int firstNumber;
        int secondNumber;
        int result;
        String[] romanNumbers = {"0", "I", "II", "III", "IV",
                                 "V", "VI", "VII", "VIII", "IX",
                                 "X", "XI", "XII", "XIII", "XIV",
                                  "XV", "XVI", "XVII", "XVIII", "XIX", "XX"};

        if (expression.length > 3) {
            throw new RuntimeException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        } else if (expression.length < 3) {
            throw new RuntimeException("Строка не является математической операцией");
        }


        if (Arrays.asList(romanNumbers).contains(expression[0]) && Arrays.asList(romanNumbers).contains(expression[2])) {
            firstNumber = Arrays.asList(romanNumbers).indexOf(expression[0]);
            secondNumber = Arrays.asList(romanNumbers).indexOf(expression[2]);
            result = calculateNumbers(firstNumber, secondNumber, expression[1]);
            if (result < 0) {
                throw new RuntimeException("В римской системе нет отрицательных чисел.");
            }
            return romanNumbers[result];
        } else if (!Arrays.asList(romanNumbers).contains(expression[0]) && !Arrays.asList(romanNumbers).contains(expression[2])) {
            return String.valueOf(calculateNumbers(Integer.parseInt(expression[0]), Integer.parseInt(expression[2]), expression[1]));
        } else {
            throw new RuntimeException("Используются разные системы счисления.");
        }
    }



}
