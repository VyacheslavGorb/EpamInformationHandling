package com.gorb.texthandling.entity;

public enum ComponentType {
    TEXT("\n\t"),
    PARAGRAPH(" "),
    SENTENCE(" "),
    LEXEME(""),
    WORD(""),
    EXPRESSION(""),
    SYMBOL_LEAF(""),
    PUNCTUATION_LEAF(""),
    EXPRESSION_LEAF("");

    private final String delimiter;

    ComponentType(String delimiter){
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return delimiter;
    }
}