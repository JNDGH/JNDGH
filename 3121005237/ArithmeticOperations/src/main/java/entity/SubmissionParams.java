package entity;

import exception.ArgsException;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.cli.*;

@Data
@ToString
public class SubmissionParams {

    public static final String EXERCISE_NUMBER_IDENTIFIER = "n";

    public static final String NUMBER_RANGE_IDENTIFIER = "r";

    public static final String EXERCISE_FILE_NAME_IDENTIFIER = "e";

    public static final String ANSWER_FILE_NAME_IDENTIFIER = "a";

    public static final String GENERATE_EXERCISE_FILE_NAME = "Exercises.txt";

    public static final String CHECK_RESULT_FILE_NAME = "Grade.txt";


    /**
     * 题目个数
     */
    private Integer exerciseNumber;

    private Integer numberRange;

    private String exerciseFileName;

    private String answerFileName;

    public static SubmissionParams buildSubmissionParams(String[] args) throws ArgsException {
        SubmissionParams params = new SubmissionParams();
        // 根据命令行参数定义Option对象，第1/2/3/4个参数分别是指命令行参数名缩写、参数名全称、是否有参数值、参数描述
        Option opt1 = new Option(EXERCISE_NUMBER_IDENTIFIER, "exerciseNumber", true, "生成题目的个数");
        Option opt2 = new Option(NUMBER_RANGE_IDENTIFIER, "numberRange", true, "题目中数值（自然数、真分数和真分数分母）的范围");
        Option opt3 = new Option(EXERCISE_FILE_NAME_IDENTIFIER, "exerciseFileName", true, "所检测的题目");
        Option opt4 = new Option(ANSWER_FILE_NAME_IDENTIFIER, "answerFileName", true, "所检测的题目答案");


        Options options = new Options();
        options.addOption(opt1).addOption(opt2).addOption(opt3).addOption(opt4);

        CommandLine cli = null;
        CommandLineParser cliParser = new DefaultParser();

        try {
            cli = cliParser.parse(options, args);
        } catch (ParseException e) {
            throw new ArgsException(e.getLocalizedMessage(), options);
        }
        //根据不同参数执行不同逻辑
        if (cli.hasOption(EXERCISE_NUMBER_IDENTIFIER) || cli.hasOption(NUMBER_RANGE_IDENTIFIER)) {
            params.setExerciseNumber(Integer.parseInt(cli.getOptionValue(EXERCISE_NUMBER_IDENTIFIER, "10")));
            if (!cli.hasOption(NUMBER_RANGE_IDENTIFIER)) {
                throw new ArgsException("若要使用生成四则算术功能,则 r 参数为必选", options);
            }
            params.setNumberRange(Integer.parseInt(cli.getOptionValue(NUMBER_RANGE_IDENTIFIER)));
        }
        if (cli.hasOption(EXERCISE_FILE_NAME_IDENTIFIER) || cli.hasOption(ANSWER_FILE_NAME_IDENTIFIER)) {
            if (cli.hasOption(EXERCISE_FILE_NAME_IDENTIFIER) && cli.hasOption(ANSWER_FILE_NAME_IDENTIFIER)) {
                params.setExerciseFileName(cli.getOptionValue(EXERCISE_FILE_NAME_IDENTIFIER));
                params.setAnswerFileName(cli.getOptionValue(ANSWER_FILE_NAME_IDENTIFIER));
            }else {
                throw new ArgsException("四则运算答案检测参数缺失", options);
            }
        }
        return params;
    }


}
