package test1;

import java.util.Stack;

public class test1 {

    public static void main(String[] args) {
        String test = "(35-(24*15-5*50)+(65+21/7))/2";
        double calculate = calculate(test);
        System.out.println(test+" = "+calculate);
    }

    // 计算给定表达式的结果
    public static double calculate(String expression) {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();
        int i = 0;

        // 遍历表达式中的每个字符
        while (i < expression.length()) {
            char ch = expression.charAt(i);

            if (ch == ' ') {
                i++;
                continue; // 忽略空格
            }

            if (Character.isDigit(ch) || ch == '.') {
                // 如果是数字或小数点，构建数字并将其推入数字栈
                StringBuilder sb = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    sb.append(expression.charAt(i));
                    i++;
                }
                numbers.push(Double.parseDouble(sb.toString()));
            } else if (ch == '(') {
                operators.push(ch); // 将左括号推入运算符栈
                i++;
            } else if (ch == ')') {
                // 处理右括号：执行操作直到遇到左括号，并将结果推入数字栈
                while (!operators.isEmpty() && operators.peek() != '(') {
                    double result = performOperation(numbers, operators);
                    numbers.push(result);
                }
                if (!operators.isEmpty() && operators.peek() == '(') {
                    operators.pop(); // 弹出左括号
                }
                i++;
            } else if (isOperator(ch)) {
                // 处理运算符：根据优先级执行操作，将当前运算符推入运算符栈
                while (!operators.isEmpty() && precedence(ch) <= precedence(operators.peek())) {
                    double result = performOperation(numbers, operators);
                    numbers.push(result);
                }
                operators.push(ch);
                i++;
            } else {
                // 处理无效字符，如果表达式包含非法字符
                throw new IllegalArgumentException("Invalid character in expression: " + ch);
            }
        }

        // 处理剩余的操作
        while (!operators.isEmpty()) {
            double result = performOperation(numbers, operators);
            numbers.push(result);
        }

        // 检查数字栈的大小，确保最终只有一个结果
        if (numbers.size() != 1) {
            throw new IllegalArgumentException("Invalid expression");
        }

        return numbers.pop(); // 返回最终结果
    }

    // 检查字符是否为运算符
    public static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    // 获取运算符的优先级
    public static int precedence(char ch) {
        if (ch == '+' || ch == '-') {
            return 1;
        } else if (ch == '*' || ch == '/') {
            return 2;
        }
        return 0;
    }

    // 执行运算
    public static double performOperation(Stack<Double> numbers, Stack<Character> operators) {
        if (numbers.size() < 2 || operators.isEmpty()) {
            throw new IllegalArgumentException("Invalid expression");
        }

        double num2 = numbers.pop();
        double num1 = numbers.pop();
        char operator = operators.pop();

        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return num1 / num2;
        }

        return 0;
    }
}

