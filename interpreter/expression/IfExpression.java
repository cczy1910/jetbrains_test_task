package interpreter.expression;

import interpreter.exceptions.*;

import java.util.HashMap;

public class IfExpression extends Expression {
    private Expression condition;
    private Expression ifTrue;
    private Expression ifFalse;
    private String asString;

    public IfExpression(Expression condition, Expression ifTrue, Expression ifFalse, int lineNumber, String asString) {
        super(lineNumber);
        this.condition = condition;
        this.ifTrue = ifTrue;
        this.ifFalse = ifFalse;
        this.asString = asString;
    }

    public String asString() {
        return this.asString;
    }

    public int execute(HashMap<String, Integer> argsSpace, HashMap<String, Function> funcSpace) {
        if (condition.evaluate(argsSpace, funcSpace) == 0) {
            return ifFalse.evaluate(argsSpace, funcSpace);
        } else {
            return ifTrue.evaluate(argsSpace, funcSpace);
        }
    }
}
