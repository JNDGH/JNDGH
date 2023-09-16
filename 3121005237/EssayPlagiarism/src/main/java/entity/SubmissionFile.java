package entity;

import lombok.Data;

@Data
public class SubmissionFile {

    /**
     * 提交原文文件名
     */
    private String originalFileName;

    /**
     * 提交待检测文件名
     */
    private String checkedFileName;

    /**
     * 结果存放文件
     */
    private String resultFileName;

    /**
     * 提交原文文件内容
     */
    private String originalFileContent;

    /**
     * 提交待检测文件内容
     */
    private String checkedFileContent;

    public SubmissionFile(String originalFileName, String checkedFileName, String resultFileName) {
        this.originalFileName = originalFileName;
        this.checkedFileName = checkedFileName;
        this.resultFileName = resultFileName;
    }
}
