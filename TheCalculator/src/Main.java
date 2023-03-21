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
            System.out.println("Введите выражение: ");
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
            default:
                System.out.println("Неверный оператор");
                break;
        }
        return result;
    }

        public static String arabicToRoman (final int number){
            if (number < 0 || 101 <= number) {
                throw new IllegalArgumentException();
            }
            int numeric;
            int numCounter = 0;
            String[] romans = {"I", "V", "X", "L", "C", "D", "M"};
            StringBuilder resultString = new StringBuilder();
            String finalString = "";
            String numberInString = String.valueOf(number);
            for (int i = numberInString.length() - 1; i >= 0; i--) {
                resultString.delete(0, resultString.length());
                numeric = Integer.parseInt(numberInString.substring(i, i + 1));
                if (1 <= numeric && numeric < 4) {
                    for (int j = 0; j < numeric; j++) {
                        resultString.append(romans[numCounter]);
                    }
                    numCounter += 2;
                } else if (4 <= numeric && numeric < 9) {
                    numCounter += 2;
                    if (numeric == 4) {
                        resultString.append(romans[numCounter - 2]);
                    }
                    resultString.append(romans[numCounter - 1]);
                    for (int j = 0; j < (numeric - 5); j++) {
                        resultString.append(romans[numCounter-2]);
                    }
                } else if (numeric == 9){
                    numCounter +=2;
                    resultString.append(romans[numCounter - 2] + romans[numCounter]);
                } else if (numeric == 0) {
                    numCounter += 2;
                }
                finalString = resultString.append(finalString).toString();
            }
            return finalString;
        }

    public static String calc(String[] expression) {
        int firstNumber;
        int secondNumber;
        int result;
        String[] romanNumbers = {"", "I", "II", "III", "IV",
                                 "V", "VI", "VII", "VIII", "IX",
                                 "X"};

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
            return arabicToRoman(result);
        } else if (!Arrays.asList(romanNumbers).contains(expression[0]) && !Arrays.asList(romanNumbers).contains(expression[2])) {
            return String.valueOf(calculateNumbers(Integer.parseInt(expression[0]), Integer.parseInt(expression[2]), expression[1]));
        } else {
            throw new RuntimeException("Используются разные системы счисления.");
        }
    }


    }
