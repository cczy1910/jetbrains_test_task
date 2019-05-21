package interpreter.expression;


import interpreter.exceptions.ArgumentNumberException;
import interpreter.exceptions.FunctionNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;

public class Call extends Expression {
    private String name;
    private String asString;
    private ArrayList<Expression> arguments;

    public Call(String name, ArrayList<Expression> arguments, int lineNumber, String asString) {
        super(lineNumber);
        this.name = name;
        this.arguments = arguments;
        this.asString = asString;
    }

    public String asString() {
        return this.asString;
    }

    public int execute(HashMap<String, Integer> argsSpace, HashMap<String, Function> funcSpace) {
        if (!funcSpace.containsKey(name)) {
            throw new FunctionNotFoundException(name, lineNumber);
        }
        ArrayList<Integer> funcArguments = new ArrayList<>();
        for (Expression e : this.arguments) {
            funcArguments.add(e.evaluate(argsSpace, funcSpace));
        }
        if (funcArguments.size() != funcSpace.get(name).getArgsNumber()) {
            throw new ArgumentNumberException(name, lineNumber);
        }
        return funcSpace.get(name).evaluate(funcArguments, funcSpace);
    }
}
