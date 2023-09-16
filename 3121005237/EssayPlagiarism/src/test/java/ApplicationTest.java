import exception.ArgsException;
import org.junit.Test;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import static org.junit.Assert.*;

public class ApplicationTest {

    @Test
    public void main() throws Exception {
        String[] args = {getResourceFileName("original.txt"),getResourceFileName("detected.txt"),getResourceFileName("result.txt")};
        Application.main(args);
        try {
            Application.main(new String[]{});
        } catch (Exception e) {
            assertTrue(e instanceof ArgsException);
        }
    }

    private String getResourceFileName(String fileName) throws UnsupportedEncodingException {
        String path = Application.class.getClassLoader().getResource(fileName).getPath();
        path = URLDecoder.decode(path,"utf-8");
        path = path.replaceFirst("/","");
        return path;
    }

}