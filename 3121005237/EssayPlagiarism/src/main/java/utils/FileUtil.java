package utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * 文件处理工具
 */
public class FileUtil {
    public static String readFileToString(String path) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        Stream<String> lines = Files.lines(Paths.get(path));
        //按行加载,防止文件过大OOM
        lines.forEachOrdered(stringBuilder::append);
        return stringBuilder.toString();
    }

    public static void writeToFile(String text, String path) throws FileNotFoundException {
        File file = new File(path);
        byte[] b = text.getBytes();
        OutputStream out = new FileOutputStream(file);

        try {
            out.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
