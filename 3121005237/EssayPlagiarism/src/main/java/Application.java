
import entity.SubmissionFile;
import exception.ArgsException;
import lombok.extern.slf4j.Slf4j;
import utils.FileUtil;
import utils.JaccardTextSimilarity;

@Slf4j
public class Application {


    public static void main(String[] args) throws Exception {
        if (args.length != ArgsException.ARGS_NUMBER) {
            log.error("传入参数个数:{}", args.length);
            throw new ArgsException();
        }
        SubmissionFile submissionFile = new SubmissionFile(args[0], args[1], args[2]);

        submissionFile.setOriginalFileContent
                (FileUtil.readFileToString(submissionFile.getOriginalFileName()));
        log.info("{} 文件读取成功...", submissionFile.getOriginalFileName());
        submissionFile.setCheckedFileContent
                (FileUtil.readFileToString(submissionFile.getCheckedFileName()));
        log.info("{} 文件读取成功...", submissionFile.getCheckedFileName());

        double similarity = JaccardTextSimilarity.getSimilarity
                (submissionFile.getOriginalFileContent(), submissionFile.getCheckedFileContent());

        String resultString = submissionFile.getOriginalFileName() +
                "与" +
                submissionFile.getCheckedFileName() +
                "的文本相似度为" +
                similarity;
        log.info(resultString);
        FileUtil.writeToFile(resultString,submissionFile.getResultFileName());
        log.info("检测结果已写入{}",submissionFile.getResultFileName());
    }
}
