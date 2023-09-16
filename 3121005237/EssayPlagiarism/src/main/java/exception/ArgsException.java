package exception;

/**
 * 参数错误异常类
 */
public class ArgsException extends Exception{

    public static final int ARGS_NUMBER = 3;

    public ArgsException(){
        super("参数数量错误");
    }
}
