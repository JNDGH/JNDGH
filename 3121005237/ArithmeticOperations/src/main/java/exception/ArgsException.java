package exception;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

public class ArgsException extends Exception{
    public ArgsException(String message, Options options){
        super(message);
        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp("options", options);
    }
}
