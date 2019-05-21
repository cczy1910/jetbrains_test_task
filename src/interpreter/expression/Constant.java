package interpreter.expression;

import java.util.HashMap;

public class Constant extends Expression {
    private int value;

    public String asString() {
        return Integer.toString(this.value);
    }

    public Constant(int value, int lineNumber) {
        super(lineNumber);
        this.value = value;
    }

    public int execute(HashMap<String, Integer> argsSpace, HashMap<String, Function> funcSpace) {
        return value;
    }
}