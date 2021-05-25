package com.gorb.texthandling.interpreter.impl;

import com.gorb.texthandling.interpreter.AbstractExpression;
import com.gorb.texthandling.interpreter.ExpressionContext;

public class NonterminalExpression implements AbstractExpression {
    private int number;

    public NonterminalExpression(int number) {
        this.number = number;
    }

    @Override
    public void interpret(ExpressionContext context) {
        context.pushValue(number);
    }
}
