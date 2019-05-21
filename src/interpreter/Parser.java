package interpreter;

import interpreter.expression.binaryExpresions.*;
import interpreter.exceptions.SyntaxException;
import interpreter.expression.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Parser {
    private String line;
    private int lineNumber = 0;
    private int index;

    private char currentChar() {
        checkBound();
        return line.charAt(index);
    }

    private boolean isCharacter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_';
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private String getIdentifier() {
        checkBound();
        int start = index;
        while (index < line.length() && isCharacter(currentChar())) {
            index++;
        }
        return line.substring(start, index);
    }

    private void checkBound() {
        if (index >= line.length()) {
            throw new SyntaxException();
        }
    }

    private void checkSubstring(String s) {
        if (index + s.length() - 1 >= line.length() || !line.substring(index, index + s.length()).equals(s)) {
            throw new SyntaxException();
        }
    }

    private Expression getExpression() {
        if (isCharacter(currentChar())) {
            int start = index;
            String identifier = getIdentifier();
            if (index < line.length() && currentChar() == '(') {
                index++;
                ArrayList<Expression> arguments = new ArrayList<>();
                arguments.add(getExpression());
                while (currentChar() == ',') {
                    index++;
                    arguments.add(getExpression());
                }
                checkSubstring(")");
                index++;
                return new Call(identifier, arguments, lineNumber, line.substring(start, index));
            } else {
                return new Identifier(identifier, lineNumber);
            }
        } else if (currentChar() == '(') {
            int start = index;
            index++;
            Expression left = getExpression();
            char operation = currentChar();
            index++;
            Expression right = getExpression();
            checkSubstring(")");
            index++;
            switch (operation) {
                case '+':
                    return new Add(left, right, lineNumber, line.substring(start, index));
                case '-':
                    return new Subtract(left, right, lineNumber, line.substring(start, index));
                case '*':
                    return new Multiply(left, right, lineNumber, line.substring(start, index));
                case '/':
                    return new Divide(left, right, lineNumber, line.substring(start, index));
                case '%':
                    return new Modul(left, right, lineNumber, line.substring(start, index));
                case '>':
                    return new More(left, right, lineNumber, line.substring(start, index));
                case '<':
                    return new Less(left, right, lineNumber, line.substring(start, index));
                case '=':
                    return new Equals(left, right, lineNumber, line.substring(start, index));
                default:
                    throw new SyntaxException();
            }
        } else if (isDigit(currentChar()) || currentChar() == '-') {
            int start = index;
            if (currentChar() == '-') {
                index++;
            }
            while (index < line.length() && isDigit(currentChar())) {
                index++;
            }
            return new Constant(Integer.parseInt(line.substring(start, index)), lineNumber);
        } else if (currentChar() == '[') {
            int start = index;
            index++;
            Expression cond = getExpression();
            checkSubstring("]?{");
            index += 3;
            Expression ifTrue = getExpression();
            checkSubstring("}:{");
            index += 3;
            Expression ifFalse = getExpression();
            checkSubstring("}");
            index++;
            return new IfExpression(cond, ifTrue, ifFalse, lineNumber, line.substring(start, index));
        } else {
            throw new SyntaxException();
        }
    }

    public Function parseFunction(String line) {
        this.line = line;
        lineNumber++;
        index = 0;
        String name = getIdentifier();
        checkSubstring("(");
        index++;
        ArrayList<String> argumentList = new ArrayList<>();
        argumentList.add(getIdentifier());
        while (currentChar() == ',') {
            index++;
            argumentList.add(getIdentifier());
        }
        checkSubstring(")={");
        index += 3;
        Expression expression = getExpression();
        checkSubstring("}");
        if (index + 1 < line.length()) {
            throw new SyntaxException();
        }
        return new Function(name, argumentList, expression);
    }

    public Expression parseExpression(String line) {
        this.line = line;
        lineNumber++;
        index = 0;
        Expression expression = getExpression();
        if (index < line.length()) {
            throw new SyntaxException();
        }
        return expression;
    }
}