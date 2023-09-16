package utils;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class StringUtilTest {

    @Test
    public void isEmpty() {
        assertTrue(StringUtil.isEmpty(""));
        assertTrue(StringUtil.isEmpty(null));
        assertFalse(StringUtil.isEmpty("good"));
    }

    @Test
    public void splitTokens() {
        String text = "你好,我喜欢English,你呢?";
        List<String> tokens = Arrays.stream(new String[]{"你", "好", "我", "喜", "欢", "English", "你", "呢"}).collect(Collectors.toList());
        assertEquals(tokens, StringUtil.splitTokens(text));
    }
}