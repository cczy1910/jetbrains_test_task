package interpreter;

import interpreter.exceptions.InterpreterException;
import interpreter.exceptions.SyntaxException;
import interpreter.expression.Expression;
import interpreter.expression.Function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

public class Interpreter {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            HashMap<String, Function> funcSpace = new HashMap<>();
            Parser parser = new Parser();
            ArrayList<String> lines = new ArrayList<>();
            String curLine = reader.readLine();
            while (curLine != null) {
                lines.add(curLine);
                curLine = reader.readLine();
            }
            for (int i = 0; i + 1 < lines.size(); i++) {
                Function curFunction = parser.parseFunction(lines.get(i));
                funcSpace.put(curFunction.getName(), curFunction);
            }
            Expression expression = parser.parseExpression(lines.get(lines.size() - 1));
            System.out.println(expression.evaluate(new HashMap<>(), funcSpace));
        } catch (InterpreterException IE) {
            System.out.println(IE.getMessage());
        }
    }
}
