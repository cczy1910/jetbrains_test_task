package interpreter.expression.binaryExpresions;

import interpreter.expression.Binary;
import interpreter.expression.Expression;

public class Equals extends Binary {
    public Equals(Expression first, Expression second, int lineNumber, String asString) {
        super(first, second, lineNumber, asString);
    }
    protected int apply(int x, int y) {
        return x == y ? 1 : 0;
    }
}