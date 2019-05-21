package interpreter.exceptions;

public class FunctionNotFoundException extends InterpreterException {
    public FunctionNotFoundException(String name, int lineNumber){
        super("FUNCTION NOT FOUND " + name + ":" + lineNumber);
    }
}
