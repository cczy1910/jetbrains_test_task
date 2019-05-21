package interpreter.expression;

import interpreter.exceptions.ParameterNotFoundException;

import java.util.HashMap;

public class Identifier extends Expression {
    private String name;

    public Identifier(String name, int lineNumber) {
        super(lineNumber);
        this.name = name;
    }

    public String asString() {
        return this.name;
    }

    public int execute(HashMap<String, Integer> argsSpace, HashMap<String, Function> funcSpace) {
        if (!argsSpace.containsKey(name)) {
            throw new ParameterNotFoundException(name, this.lineNumber);
        }
        return argsSpace.get(name);
    }
}
