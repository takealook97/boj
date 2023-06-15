import java.util.ArrayList;
import java.util.Collections;

class Solution {
    static ArrayList<Long> numSource = new ArrayList<>();
    static ArrayList<String> opSource = new ArrayList<>();
    static ArrayList<Long> numbers;
    static ArrayList<String> operators;
    static ArrayList<Long> result = new ArrayList<>();

    public static long solution(String expression) {
        makeList(expression);
        case_1();
        case_2();
        case_3();
        case_4();
        case_5();
        case_6();
        Collections.sort(result);
        return result.get(result.size() - 1);
    }

    public static void makeList(String expression) {
        String[] tempNums = expression.split("\\+|-|\\*");
        for (String num : tempNums) {
            numSource.add(Long.parseLong(num));
        }
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) < '0') {
                opSource.add(String.valueOf(expression.charAt(i)));
            }
        }
    }

    public static void multiply() {
        for (int i = 0; i < operators.size(); i++) {
            if (operators.get(i).equals("*")) {
                long result = numbers.get(i) * numbers.get(i + 1);
                numbers.add(i + 1, result);
                numbers.remove(i);
                numbers.remove(i + 1);
                operators.remove(i);
                i--;
            }
        }
    }

    public static void plus() {
        for (int i = 0; i < operators.size(); i++) {
            if (operators.get(i).equals("+")) {
                long result = numbers.get(i) + numbers.get(i + 1);
                numbers.add(i + 1, result);
                numbers.remove(i);
                numbers.remove(i + 1);
                operators.remove(i);
                i--;
            }
        }
    }

    public static void minus() {
        for (int i = 0; i < operators.size(); i++) {
            if (operators.get(i).equals("-")) {
                long result = numbers.get(i) - numbers.get(i + 1);
                numbers.add(i + 1, result);
                numbers.remove(i);
                numbers.remove(i + 1);
                operators.remove(i);
                i--;
            }
        }
    }

    public static void case_1() {
        numbers = new ArrayList<>(numSource);
        operators = new ArrayList<>(opSource);
        multiply();
        plus();
        minus();
        result.add(Math.abs(numbers.get(0)));
    }

    public static void case_2() {
        numbers = new ArrayList<>(numSource);
        operators = new ArrayList<>(opSource);
        multiply();
        minus();
        plus();
        result.add(Math.abs(numbers.get(0)));
    }

    public static void case_3() {
        numbers = new ArrayList<>(numSource);
        operators = new ArrayList<>(opSource);
        plus();
        multiply();
        minus();
        result.add(Math.abs(numbers.get(0)));
    }

    public static void case_4() {
        numbers = new ArrayList<>(numSource);
        operators = new ArrayList<>(opSource);
        plus();
        minus();
        multiply();
        result.add(Math.abs(numbers.get(0)));
    }

    public static void case_5() {
        numbers = new ArrayList<>(numSource);
        operators = new ArrayList<>(opSource);
        minus();
        multiply();
        plus();
        result.add(Math.abs(numbers.get(0)));
    }

    public static void case_6() {
        numbers = new ArrayList<>(numSource);
        operators = new ArrayList<>(opSource);
        minus();
        plus();
        multiply();
        result.add(Math.abs(numbers.get(0)));
    }
}