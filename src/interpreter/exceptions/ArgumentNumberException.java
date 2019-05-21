package interpreter.exceptions;

public class ArgumentNumberException extends InterpreterException {
    public ArgumentNumberException(String name, int lineNumber) {
        super("ARGUMENT NUMBER MISMATCH " + name + ":" + lineNumber);
    }
}
