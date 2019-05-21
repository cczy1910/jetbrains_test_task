package interpreter.exceptions;

public class ParameterNotFoundException extends InterpreterException {
    public ParameterNotFoundException(String name, int lineNumber) {
        super("PARAMETER NOT FOUND " + name + ":" + lineNumber);
    }
}
