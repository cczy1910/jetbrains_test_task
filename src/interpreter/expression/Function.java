package interpreter.expression;

import java.util.ArrayList;
import java.util.HashMap;

public class Function {
    private String name;
    private Expression expression;
    private ArrayList<String> args;

    public String getName() {
        return this.name;
    }

    public Function(String name, ArrayList<String> args, Expression expression) {
        this.name = name;
        this.expression = expression;
        this.args = args;
    }

    int getArgsNumber(){
        return args.size();
    }

    int evaluate(ArrayList<Integer> arguments, HashMap<String, Function> funcSpace) {
        HashMap<String, Integer> argsSpace = new HashMap<>();
        for (int i = 0; i < args.size(); i++) {
            argsSpace.put(args.get(i), arguments.get(i));
        }
        return expression.evaluate(argsSpace, funcSpace);
    }
}
