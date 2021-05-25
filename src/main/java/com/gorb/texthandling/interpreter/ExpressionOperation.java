package com.gorb.texthandling.interpreter;

import java.util.HashMap;
import java.util.Map;

public enum ExpressionOperation {
    OR("|", 1),
    XOR("^", 2),
    AND("&", 3),
    SIGNED_SHIFT_RIGHT(">>", 5),
    UNSIGNED_SHIFT_RIGHT(">>>", 6),
    SIGNED_SHIFT_LEFT("<<", 4),
    NOT("~", 7),
    OPEN_BRACKET("(", Integer.MAX_VALUE),
    CLOSE_BRACKET(")", Integer.MAX_VALUE);

    static Map<String, ExpressionOperation> valuesMap = new HashMap<>();
    private final String stringValue;
    private final int precedence;

    static {
        for (ExpressionOperation operation : ExpressionOperation.values()) {
            valuesMap.put(operation.stringValue, operation);
        }
    }

    ExpressionOperation(String stringValue, int precedence) {
        this.stringValue = stringValue;
        this.precedence = precedence;
    }

    static public ExpressionOperation getOperationByString(String s) {
        if (!valuesMap.containsKey(s)) {
            throw new IllegalArgumentException("Illegal value " + s);
        }
        return valuesMap.get(s);
    }

    public int getPrecedence() {
        return precedence;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}
