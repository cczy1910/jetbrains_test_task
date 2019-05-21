package interpreter.expression;

import interpreter.exceptions.InterpreterException;
import interpreter.exceptions.InterpreterRuntimeException;

import java.util.HashMap;

public abstract class Expression {
    protected int lineNumber;

    protected Expression(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public abstract String asString();

    protected abstract int execute(HashMap<String, Integer> argsSpace, HashMap<String, Function> funcSpace);

    public int evaluate(HashMap<String, Integer> argsSpace, HashMap<String, Function> funcSpace) {
        try {
            return execute(argsSpace, funcSpace);
        } catch (InterpreterException IE) {
            throw IE;
        } catch (RuntimeException RE) {
            throw new InterpreterRuntimeException(asString(), lineNumber);
        }
    }
}
