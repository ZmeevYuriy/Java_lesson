package Lesson_1;

import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        System.out.println(MyCalc.factorial(5));

        System.out.println(MyCalc.triangleDigit(10));

        System.out.println(MyCalc.calc(9, 5, "+"));
        System.out.println(MyCalc.calc(9, 5, "-"));
        System.out.println(MyCalc.calc(9, 0, "/"));
        System.out.println(MyCalc.calc(9, 5, "*"));

        MyCalc.showSimpleDigit(1000);
        System.out.println("Введите пример: ");
    }
}

abstract class MyCalc {
    public static int factorial(int a) {
        int res = 1;
        while (a > 1) {
            res *= a;
            a--;
        }
        return res;
    }

    public static int triangleDigit(int n) {
        return (int) (0.5 * n * (n + 1));
    }

    public static float calc(float num1, float num2, String sign) {
        float res = 0f;
        String plus = "+", minus = "-", delimiter = "/", multiple = "*";
        try {
            if (Objects.equals(sign, plus)) res = num1 + num2;
            if (Objects.equals(sign, minus)) res = num1 - num2;
            if (Objects.equals(sign, multiple)) res = num1 * num2;
            if (Objects.equals(sign, delimiter)) res = num1 / num2;
        } catch (Exception e) {
            
            System.out.println(e.getMessage());
        }
        
        return res;
        
    }

    public static void showSimpleDigit(int num) {
        if (num >= 2) {
            System.out.println(2);
        }
        for (int i = 3; i <= num; i += 2) {
            if (isSimple(i)) {
                System.out.println(i);
            }
        }
    }


    public static boolean isSimple(int num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}

