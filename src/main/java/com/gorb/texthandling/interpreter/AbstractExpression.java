package com.gorb.texthandling.interpreter;

@FunctionalInterface
public interface AbstractExpression {
    void interpret(ExpressionContext context);
}
