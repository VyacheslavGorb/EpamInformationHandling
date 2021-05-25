package com.gorb.texthandling.interpreter;

import java.util.ArrayDeque;
import java.util.Deque;

public class ExpressionContext {
    private Deque<Integer> contextValues = new ArrayDeque<>();

    public Integer popValue() {
        return contextValues.pop();
    }

    public void pushValue(Integer value) {
        contextValues.push(value);
    }
}
