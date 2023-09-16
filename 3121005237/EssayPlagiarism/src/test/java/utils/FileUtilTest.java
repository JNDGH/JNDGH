package utils;

import org.junit.Test;

import java.io.*;
import java.net.URLDecoder;


public class FileUtilTest {

    @Test
    public void writeToFile() throws FileNotFoundException, UnsupportedEncodingException {
        String fileName = "text.txt";
        String path = this.getClass().getClassLoader().getResource(fileName).getPath();
        path = URLDecoder.decode(path,"utf-8");
        FileUtil.writeToFile("Hello,world!",path);
    }


    @Test
    public void readFileToString() throws IOException {
        String fileName = "text.txt";
        String path = this.getClass().getClassLoader().getResource(fileName).getPath();
        path = URLDecoder.decode(path,"utf-8");
        path = path.replaceFirst("/","");
        String s = FileUtil.readFileToString(path);
        System.out.println(s);
    }


}