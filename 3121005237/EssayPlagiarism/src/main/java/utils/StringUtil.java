package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理工具
 */
public class StringUtil {
    /**
     * 判断字符串是否为空
     * @param s 字符串
     * @return
     */
    public static boolean isEmpty(String s){
        return s == null || s.isEmpty();
    }

    /**
     * 将text文本分解为汉字或者单词
     * @param text 字符串
     * @return
     */
    public static List<String> splitTokens(String text) {
        List<String> result = new ArrayList<>();
        // 使用Unicode编码的组合字符范围匹配中文字符
        String regex = "\\p{Script=Han}\\p{M}*+|[a-zA-Z]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            result.add(matcher.group());
        }
        return result;
    }
}
