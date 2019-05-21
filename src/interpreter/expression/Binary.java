package interpreter.expression;

import java.util.HashMap;

public abstract class Binary extends Expression {
    private Expression first;
    private Expression second;
    private String asString;

    protected Binary(Expression first, Expression second, int lineNumber, String asString) {
        super(lineNumber);
        this.first = first;
        this.second = second;
        this.asString = asString;
    }

    protected abstract int apply(int x, int y);

    public String asString() {
        return asString;
    }

    public int execute(HashMap<String, Integer> argsSpace, HashMap<String, Function> funcSpace) {
        return apply(first.evaluate(argsSpace, funcSpace), second.evaluate(argsSpace, funcSpace));
    }
}
