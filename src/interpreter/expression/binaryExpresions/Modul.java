package interpreter.expression.binaryExpresions;

import interpreter.expression.Binary;
import interpreter.expression.Expression;

public class Modul extends Binary {
    public Modul(Expression first, Expression second, int lineNumber, String asString) {
        super(first, second, lineNumber, asString);
    }
    protected int apply(int x, int y) {
        return x % y;
    }
}