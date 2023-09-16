import org.junit.Test;
import utils.JaccardTextSimilarity;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EssayTest {

    @Test
    public void test(){
        String s1 = "我喜欢English and 语文";
        String s2 = "I like English and 语文 and Chinese";
        double similarity = JaccardTextSimilarity.getSimilarity(s1, s2);
        System.out.println(similarity);
    }



}
