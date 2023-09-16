package util;

import cn.hutool.core.io.FileUtil;
import entity.SubmissionParams;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 扬
 */
public class ArithmeticUtil {

    /**
     * 生成小学四则运算题目并写入Exercises.txt文件
     *
     * @param exerciseNumber 生成个数
     * @param numberRange    题目中数值（自然数、真分数和真分数分母）的范围
     */
    public static void buildArithmetic(int exerciseNumber, int numberRange) {
        Random random = new Random();
        List<String> arithmeticList = new LinkedList<>();
        DataGenerator dataGenerator = new DataGenerator();
        for (int i = 1; i <= exerciseNumber; i++) {
            //限制每道题目中出现的运算符个数不超过3个
            int operatorNumber = random.nextInt(3)+1;
            StringBuilder arithmetic = new StringBuilder();
            switch (operatorNumber) {
                case 1:
                    // 9 + 5
                    arithmetic.append(dataGenerator.generateNumber(numberRange))
                            .append(dataGenerator.randomOperator())
                            .append(dataGenerator.generateNumber(numberRange))
                            .append(" =");
                    break;
                case 2:
                    //5 × 3 -2
                    arithmetic.append(dataGenerator.generateLeftParenthesis(1))
                            .append(dataGenerator.generateNumber(numberRange))
                            .append(dataGenerator.randomOperator())
                            .append(dataGenerator.generateLeftParenthesis(3))
                            .append(dataGenerator.generateNumber(numberRange))
                            .append(dataGenerator.generateRightParenthesis(4, 6))
                            .append(dataGenerator.randomOperator())
                            .append(dataGenerator.generateNumber(numberRange))
                            .append(dataGenerator.generateRightParenthesis(6, 6))
                            .append(" =");
                    break;
                case 3:
                    // 5 × 3 -2 ÷ 8
                    arithmetic.append(dataGenerator.generateLeftParenthesis(1))
                            .append(dataGenerator.generateNumber(numberRange))
                            .append(dataGenerator.randomOperator())
                            .append(dataGenerator.generateLeftParenthesis(3))
                            .append(dataGenerator.generateNumber(numberRange))
                            .append(dataGenerator.generateRightParenthesis(4, 8))
                            .append(dataGenerator.randomOperator())
                            .append(dataGenerator.generateLeftParenthesis(5))
                            .append(dataGenerator.generateNumber(numberRange))
                            .append(dataGenerator.generateRightParenthesis(6, 8))
                            .append(dataGenerator.randomOperator())
                            .append(dataGenerator.generateNumber(numberRange))
                            .append(dataGenerator.generateRightParenthesis(8, 8))
                            .append(" =");
                    break;
                default:
                    break;
            }
            try {
                RPN.calculateInfixExpression(arithmetic.toString());
                arithmeticList.add(i + ". " + arithmetic);
            } catch (Exception e) {
                i--;
            }
        }
        FileUtil.writeLines(arithmeticList,SubmissionParams.GENERATE_EXERCISE_FILE_NAME,"utf-8");
    }

    /**
     * 检查小学四则运算题目答案并将结果写入Grade.txt文件
     *
     * @param exerciseFileName 待检测题目文件名
     * @param answerFileName   待检测题目答案文件名
     */
    public static void checkExercise(String exerciseFileName, String answerFileName) {
List<String> exercises = FileUtil.readLines(exerciseFileName, StandardCharsets.UTF_8);
        Map<String, String> answerMap = new HashMap<>();
        List<String> answers = FileUtil.readLines(answerFileName, StandardCharsets.UTF_8);
        String pattern = "(^[0-9]+). (.*)";
        Pattern r = Pattern.compile(pattern);
        answers.forEach(a -> {
            Matcher m = r.matcher(a);
            if (m.find()) {
                answerMap.put(m.group(1), m.group(2));
            }
        });
        List<String> trueExercise = new LinkedList<>();
        List<String> wrongExercise = new LinkedList<>();
        for (int i = 0; i < exercises.size(); i++) {
            Matcher m = r.matcher(exercises.get(i));
            if (m.find()) {
                String index = m.group(1);
                String arithmetic = m.group(2);
                String answer = RPN.calculateInfixExpression(arithmetic);
                if (answer.equals(answerMap.get(index))) {
                    trueExercise.add(index);
                } else {
                    wrongExercise.add(index);
                }
            }
        }
        StringBuilder fileContent = new StringBuilder().append("Correct: ").append(trueExercise.size()).append(" (");
        for (int i = 0; i < trueExercise.size() ; i++) {
            fileContent.append(trueExercise.get(i)).append(i == trueExercise.size()-1 ? "":",");
        }
        fileContent.append(")\n");
        fileContent.append("Wrong: ").append(wrongExercise.size()).append(" (");
        for (int i = 0; i < wrongExercise.size() ; i++) {
            fileContent.append(wrongExercise.get(i)).append(i == wrongExercise.size()-1 ? "":",");
        }
        fileContent.append(")");
        FileUtil.writeString(fileContent.toString(), SubmissionParams.CHECK_RESULT_FILE_NAME, "utf-8");
    }
}
