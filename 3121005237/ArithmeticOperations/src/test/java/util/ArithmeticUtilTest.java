package util;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class ArithmeticUtilTest {

    @Test
    public void buildArithmetic() {
        ArithmeticUtil.buildArithmetic(10,10);
    }

    @Test
    public void checkExercise() {
        String path = "E:\\大二下\\软件工程\\3121005221\\3121005221\\ArithmeticOperations\\target\\test-classes\\";
        ArithmeticUtil.checkExercise(path+"e.txt",path+"a.txt");
    }
}