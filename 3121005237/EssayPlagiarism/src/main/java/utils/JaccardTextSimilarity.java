package utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * 字符串查重工具(Jaccard相似系数查重方法)
 * @author 扬
 */
public class JaccardTextSimilarity {

    /**
     * 获取2段text文本的相似度
     * @param text1 文本1
     * @param text2 文本2
     * @return 相似度
     */
    public static double getSimilarity(String text1 , String text2){
        if (StringUtil.isEmpty(text1) && StringUtil.isEmpty(text2)) {
            return 1.0;
        }
        if (StringUtil.isEmpty(text1) || StringUtil.isEmpty(text2)) {
            return 0.0;
        }
        if (text1.equalsIgnoreCase(text2)) {
            return 1.0;
        }
        List<String> tokens1 = StringUtil.splitTokens(text1);
        List<String> tokens2 = StringUtil.splitTokens(text2);
        double score = getScore(tokens1, tokens2);
        //四舍五入
        score = (int) (score * 1000000 + 0.5) / (double) 1000000;
        return score;
    }

    /**
     * 求相似度分值
     * @param tokens1 text文本的token集合
     * @param tokens2 text文本的token集合
     * @return 相似度分值
     */
    private static double getScore(List<String> tokens1 ,List<String> tokens2){

        Set<String> tokens2Set = new HashSet<>(tokens2);
        //求交集
        Set<String> intersectionSet = new ConcurrentSkipListSet<>();
        tokens1.parallelStream().forEach(token -> {
            if (tokens2Set.contains(token)) {
                intersectionSet.add(token);
            }
        });
        //交集的大小
        int intersectionSize = intersectionSet.size();
        //求并集
        Set<String> unionSet = new HashSet<>();
        unionSet.addAll(tokens1);
        unionSet.addAll(tokens2);
        //并集的大小
        int unionSize = unionSet.size();
        //相似度分值
        return intersectionSize / (double) unionSize;
    }

}
