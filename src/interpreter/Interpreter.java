package interpreter;

import interpreter.exceptions.InterpreterException;
import interpreter.expression.Expression;
import interpreter.expression.Function;

import java.util.HashMap;

public class Interpreter {
    public static String interpretate(String[] lines) {
        try {
            HashMap<String, Function> funcSpace = new HashMap<>();
            Parser parser = new Parser();
            for (int i = 0; i + 1 < lines.length; i++) {
                Function curFunction = parser.parseFunction(lines[i]);
                funcSpace.put(curFunction.getName(), curFunction);
            }
            Expression expression = parser.parseExpression(lines[lines.length - 1]);
            return Integer.toString(expression.evaluate(new HashMap<>(), funcSpace));
        } catch (InterpreterException IE) {
            return IE.getMessage();
        }
    }
}
