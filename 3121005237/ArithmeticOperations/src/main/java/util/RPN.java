package util;

import java.util.*;

public class RPN {

    private static final Map<String,Integer> weightMap = new HashMap<>();

    static {
        weightMap.put("+",1);
        weightMap.put("-",1);
        weightMap.put("×",2);
        weightMap.put("÷",3);
    }

    public static String calculateInfixExpression(String expression) {
        expression = expression.replaceAll(" ","").replaceAll("=","");
        String[] tokens = expression.split("(?<=[+\\-×÷()])|(?=[+\\-×÷()])");
        List<String> infixExpressionList = Arrays.asList(tokens);

        List<String> parseSuffixExpressionList = parseSuffixExpressionList(infixExpressionList);

        return calculate(parseSuffixExpressionList);

    }

    /**
     * 将得到的中缀表达式对应的list 转换成 后缀表达式对应的list
     * @param ls
     * @return
     */
    private static List<String> parseSuffixExpressionList(List<String> ls) {
        // 定义两个栈 s1为符号栈 s2为存储中间结果的栈，因为该栈一直没有弹出过，所以使用List存储
        Stack<String> s1 = new Stack<>();
        List<String> s2 = new ArrayList<>();

        // 遍历ls
        for (String item : ls) {
            //如果是一个数就入栈，入栈s2,\\d+ 匹配数字且至少出现一次
            if (isNumber(item)) {
                s2.add(item);
            } else if (item.equals("(")) {
                // 如果是左括号就入栈，入栈s1
                s1.push(item);
            } else if (item.equals(")")) {
                // 如果是右括号，则依次弹出S1栈顶的运算符，并压入S2，直到遇到左括号为止
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                // 将 ( 弹出s1栈，消除括号，小括号
                s1.pop();
            } else {
                // 当 item 的优先级，小于或者等于栈顶运算符的优先级的时候，就应该将s1栈顶的运算夫弹出并压入s2中
                while (s1.size() != 0 && getWeight(s1.peek()) >= getWeight(item)) {
                    s2.add(s1.pop());
                }
                // 还需要将item压入栈
                s1.push(item);
            }
        }
        // 将s1中剩余的元素压入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }


    private static boolean isNumber(String token){
        return token.matches("\\d+|\\d+/\\d+|\\d+’?\\d+/\\d+");
    }

    private static String calculate(List<String> ls) {
        // 创建一个栈,只需要一个栈即可
        Stack<String> stack = new Stack<>();
        // 遍历 ls
        for (String item : ls) {
            if (isNumber(item)) {
                stack.push(item);
            } else {
                // pop 出两个数并运算，在入栈
                String num2 = stack.pop();
                String num1 = stack.pop();
                String res;
                Fraction fraction1 = new Fraction(num1);
                Fraction fraction2 = new Fraction(num2);
                if ("+".equals(item)) {
                    res = fraction1.add(fraction2).toString();
                } else if ("-".equals(item)) {
                    res = fraction1.subtract(fraction2).toString();
                } else if ("×".equals(item)) {
                    res = fraction1.multiply(fraction2).toString();
                } else if ("÷".equals(item)) {
                    res = fraction1.divide(fraction2).toString();
                } else {
                    throw new RuntimeException("符号有问题");
                }
                stack.push(res);
            }
        }
        // 最后留在stack的数据是运算结果
        return stack.pop();
    }

    private static int getWeight(String s){
        Integer weight = weightMap.get(s);
        return weight == null ? 0 : weight;
    }

}