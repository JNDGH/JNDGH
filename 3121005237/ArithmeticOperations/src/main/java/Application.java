

import entity.SubmissionParams;
import lombok.extern.slf4j.Slf4j;
import util.ArithmeticUtil;

/**
 *
 * 使用 -n 参数控制生成题目的个数
 * 使用 -r 参数控制题目中数值（自然数、真分数和真分数分母）的范围
 * 程序支持对给定的题目文件和答案文件，判定答案中的对错并进行数量统计，输入参数如下：
 * Myapp.exe -e <exercisefile>.txt -a <answerfile>.txt
 */
@Slf4j
public class Application {


    public static void main(String[] args) throws Exception {
        SubmissionParams submissionParams = SubmissionParams.buildSubmissionParams(args);
        System.out.println(submissionParams);
        //生成小学四则运算题目
        if (submissionParams.getExerciseNumber()!=null) {
            ArithmeticUtil.buildArithmetic(submissionParams.getExerciseNumber(), submissionParams.getNumberRange());
            log.info("小学四则运算题目生成成功,结果已保存至 {}",SubmissionParams.GENERATE_EXERCISE_FILE_NAME);
        }
        if (submissionParams.getExerciseFileName()!=null){
            ArithmeticUtil.checkExercise(submissionParams.getExerciseFileName(),submissionParams.getAnswerFileName());
            log.info("小学四则运算题目答案检测成功,已保存至 {}",SubmissionParams.CHECK_RESULT_FILE_NAME);
        }
    }
}
