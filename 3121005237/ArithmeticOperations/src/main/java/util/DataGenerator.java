package util;

import java.util.Random;

public class DataGenerator {

    private Random random = new Random();

    /**
     * 左括号的索引位置
     */
    private int parenthesis = -1;

    /**
     * 30% 分数 , 70% 自然数
     *
     * @param numberRange
     * @return
     */
    public String generateNumber(int numberRange) {
        int r = random.nextInt(10);
        return r < 3 ? randProperFraction(numberRange) : randomN(numberRange);
    }

    /**
     * 生成左括号的概率为20%
     */
    public String generateLeftParenthesis(int index) {
        if (parenthesis == -1) {
            if (random.nextInt(5) == 0) {
                parenthesis = index;
                return "(";
            }
        }
        return "";
    }

    /**
     * 生成右括号的概率
     *
     * @param index 当前所在的索引位置
     * @param eof   所有的索引数目
     * @return
     */
    public String generateRightParenthesis(int index, int eof) {
        if (parenthesis != -1 && index - parenthesis != 1) {
            if (random.nextInt(eof - parenthesis) == 0 || (parenthesis == 1 && eof - 2 == index)
                    || index == eof) {
                parenthesis = -1;
                return ")";
            }
        }
        return "";
    }

    /**
     * 随机生成运算符 (运算符：+, −, ×, ÷)
     *
     * @return +, −, ×, ÷
     */
    public String randomOperator() {
        String operator = null;
        int randomInt = random.nextInt(4);
        switch (randomInt) {
            case 0:
                operator = " + ";
                break;
            case 1:
                operator = " - ";
                break;
            case 2:
                operator = " × ";
                break;
            case 3:
                operator = " ÷ ";
                break;
            default:
                break;
        }
        return operator;
    }

    /**
     * 自然数：0, 1, 2, …。
     *
     * @param numberRange 范围
     * @return
     */
    public String randomN(int numberRange) {
        return String.valueOf(random.nextInt(numberRange));
    }

    /**
     * 生成真分数 (真分数：1/2, 1/3, 2/3, 1/4, 1’1/2, …)
     *
     * @param numberRange
     * @return
     */
    public String randProperFraction(int numberRange) {
        int denominator = random.nextInt(numberRange - 1) + 1;
        int molecule = random.nextInt(numberRange * denominator - 1) + 1;
        if (molecule >= denominator) {
            int N = molecule / denominator;
            molecule %= denominator;
            return molecule == 0 ? String.valueOf(N) : N + "’" + molecule + "/" + denominator;
        }
        return molecule + "/" + denominator;
    }
}
