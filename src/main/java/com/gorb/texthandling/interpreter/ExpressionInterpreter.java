package com.gorb.texthandling.interpreter;

import com.gorb.texthandling.interpreter.impl.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ExpressionInterpreter {
    private static final Logger logger = LogManager.getLogger();
    private static final String INFIX_EXPRESSION_SPLIT_REGEX =
            "(?=>{3}|((?<!>)>{2}(?!>))|<{2}|\\^|\\||&|~|\\(|\\)|((?<!\\d)\\d+(?!\\d))(?<=\\d))";
    private static final String NUMBER_REGEX = "\\d+";
    private ArrayList<AbstractExpression> listExpression;

    public ExpressionInterpreter(String infixExpression) {
        listExpression = new ArrayList<>();
        parse(infixExpression);
    }

    public Integer calculate() {
        ExpressionContext context = new ExpressionContext();
        for (AbstractExpression expression : listExpression) {
            expression.interpret(context);
        }
        logger.log(Level.INFO, "Expression calculated successfully");
        return context.popValue();
    }

    private void parse(String infixExpression) {
        String[] elementArray = infixExpression.split(INFIX_EXPRESSION_SPLIT_REGEX);
        List<String> postfixForm = convertInfixToPostfix(elementArray);
        for (String lexeme : postfixForm) {
            switch (lexeme) {
                case "^":
                    listExpression.add(new TerminalXorExpression());
                    break;
                case "|":
                    listExpression.add(new TerminalOrExpression());
                    break;
                case "&":
                    listExpression.add(new TerminalAndExpression());
                    break;
                case ">>":
                    listExpression.add(new TerminalSignedRightShiftExpression());
                    break;
                case ">>>":
                    listExpression.add(new TerminalUnsignedRightShiftExpression());
                    break;
                case "<<":
                    listExpression.add(new TerminalLeftShiftExpression());
                    break;
                case "~":
                    listExpression.add(new TerminalNotExpression());
                    break;
                default:
                    listExpression.add(new NonterminalExpression(Integer.parseInt(lexeme)));
            }
        }
        logger.log(Level.INFO, "Expression parsed successfully");
    }

    private List<String> convertInfixToPostfix(String[] infixForm) {
        List<String> result = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();
        for (String s : infixForm) {
            if (s.matches(NUMBER_REGEX)) {
                result.add(s);
            } else if (s.equals(ExpressionOperation.OPEN_BRACKET.toString())) {
                stack.push(ExpressionOperation.OPEN_BRACKET.toString());
            } else if (s.equals(ExpressionOperation.CLOSE_BRACKET.toString())) {
                while (!stack.isEmpty() && !stack.peek().equals(ExpressionOperation.OPEN_BRACKET.toString())) {
                    result.add(stack.pop());
                }
                stack.pop();
            } else {
                int currentElementPrecedence = ExpressionOperation.getOperationByString(s).getPrecedence();
                while (!stack.isEmpty()
                        && ExpressionOperation.getOperationByString(stack.peek()).getPrecedence() <= currentElementPrecedence) {
                    result.add(stack.pop());
                }
                stack.push(s);
            }
        }
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        logger.log(Level.INFO, "Expression successfully converted to infix form");
        return result;
    }
}
