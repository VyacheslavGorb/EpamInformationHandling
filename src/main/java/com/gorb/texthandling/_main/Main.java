package com.gorb.texthandling._main;

import com.gorb.texthandling.creator.TextParserCreator;
import com.gorb.texthandling.exception.TextException;
import com.gorb.texthandling.parser.InformationParser;

public class Main {
    public static void main(String[] args) throws TextException {
        String text = "It has survived - not only (five) centuries, but also the leap into 13<<2 electronic typesetting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";

        InformationParser textParser = TextParserCreator.createParser();
        var res = textParser.parse(text);

        String line = "6";
//        ExpressionInterpreter interpreter = new ExpressionInterpreter(line);
//        System.out.println(interpreter.calculate());
    }
}
