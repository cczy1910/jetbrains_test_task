package interpreter.exceptions;

public abstract class InterpreterException extends RuntimeException {
    InterpreterException(String message) {
        super(message);
    }
}
