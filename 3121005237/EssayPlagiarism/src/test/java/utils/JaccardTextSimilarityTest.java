package utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class JaccardTextSimilarityTest {

    @Test
    public void getSimilarity() {
        String text1 = "你好,I come from China and 我的兴趣爱好是看书";
        String text2 = "Hello, I come from China and my hobby is reading";
        double similarity = JaccardTextSimilarity.getSimilarity(text1,text2);
        System.out.println(similarity);
    }
}