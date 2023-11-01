package test1;

import java.util.Stack;

/**
 * author:zhaopan
 * 简单计算器
 * date:2023.10.8
 */
public class calculate {

    public static void main(String[] args) {
        String test = "(35-(24*15-5*50)+(65+21/7))/2";
        double calculate = calculate(test);

        System.out.println(test+" = "+calculate);
    }

    //计算器主方法
    public static double calculate(String expression){
        if(expression.isEmpty()){
            return 0;
        }
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();
        int i = 0;

        //遍历表达式的每一个字符
        while(i < expression.length()){
            char cr = expression.charAt(i);
            //字符为空时跳过
            if(cr == ' '){
                i++;
                continue;
            }
            //如果是数字，插入到numbers栈中
            if(Character.isDigit(cr) || cr == '.'){
                StringBuilder sb = new StringBuilder();
                while(i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')){
                    sb.append(expression.charAt(i));
                    i++;
                }
                numbers.push(Double.parseDouble(sb.toString()));
            }else if(cr == '('){ //遇到左括号直接入栈
                operators.push(cr);
                i++;
            }else if(cr == ')'){ //遇到右括号，执行运算并将结果存入栈，直到遇到左括号
                while(!operators.isEmpty() && operators.peek() != '('){
                    double op = operation(numbers, operators);
                    numbers.push(op);
                }
                if(!operators.isEmpty() && operators.peek() == '('){
                    operators.pop();
                    i++;
                }
            } else if(isPriority(cr)){ //遇到运算符，判断运算符优先级进行计算
                while(!operators.isEmpty() && priorityLevel(cr) <= priorityLevel(operators.peek())){
                    double op = operation(numbers, operators);
                    numbers.push(op);
                }
                operators.push(cr);
                i++;
            }else{ //无效字符
                throw new IllegalArgumentException("无效字符："+cr);
            }
        }
        //处理剩余操作
        while(!operators.isEmpty()) {
            double op = operation(numbers, operators);
            numbers.push(op);
        }
        //确定结果只有一个
        if(numbers.size() != 1){
            throw  new IllegalArgumentException("表达式有问题");
        }
        return numbers.pop();
    }

    //判断是否为运算符
    public static boolean isPriority(char cr){
        return cr == '+' || cr == '-' || cr == '*' || cr == '/';
    }

    //判断运算符的优先级
    public static int priorityLevel(char cr){
        if(cr == '+' || cr == '-'){
            return 1;
        }else if(cr == '*' || cr == '/'){
            return 2;
        }
        return 0;
    }

    //运算方法
    public static double operation(Stack<Double> numbers,Stack<Character> operators){
        if(numbers.size() <2 || operators.size() <1){
            throw new IllegalArgumentException("表达式有问题");
        }
        Double number1 = numbers.pop();
        Double number2 = numbers.pop();
        char operator = operators.pop();
        switch(operator){
            case '+': return number1+number2;
            case '-': return number2-number1;
            case '*': return number1*number2;
            case '/':
                if(number1 == 0){
                    throw new ArithmeticException("除数为0");
                }
                return number2/number1;
        }
        return 0;
    }
}
