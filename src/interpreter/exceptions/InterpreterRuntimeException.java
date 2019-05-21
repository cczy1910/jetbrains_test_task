package interpreter.exceptions;

public class InterpreterRuntimeException extends InterpreterException{
    public InterpreterRuntimeException(String expression, int lineNumber) {
        super("RUNTIME ERROR " + expression + ":" + lineNumber);
    }
}
