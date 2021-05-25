package com.gorb.texthandling.interpreter.impl;

import com.gorb.texthandling.interpreter.AbstractExpression;
import com.gorb.texthandling.interpreter.ExpressionContext;

public class TerminalOrExpression implements AbstractExpression {
    @Override
    public void interpret(ExpressionContext context) {
        context.pushValue(context.popValue() | context.popValue());
    }
}
