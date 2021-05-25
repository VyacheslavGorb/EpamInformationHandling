package com.gorb.texthandling.interpreter.impl;

import com.gorb.texthandling.interpreter.AbstractExpression;
import com.gorb.texthandling.interpreter.ExpressionContext;

public class TerminalNotExpression implements AbstractExpression {
    @Override
    public void interpret(ExpressionContext context) {
        context.pushValue(~context.popValue());
    }
}
