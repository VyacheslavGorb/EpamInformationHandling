package com.gorb.texthandling.parser.impl;

import com.gorb.texthandling.entity.ComponentType;
import com.gorb.texthandling.entity.InformationComponent;
import com.gorb.texthandling.entity.impl.SymbolLeaf;
import com.gorb.texthandling.entity.impl.TextComposite;
import com.gorb.texthandling.exception.TextException;
import com.gorb.texthandling.interpreter.ExpressionInterpreter;
import com.gorb.texthandling.parser.InformationParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExpressionParser implements InformationParser {

    private static final Logger logger = LogManager.getLogger();
    private static final String SPLIT_REGEX = "";

    @Override
    public InformationComponent parse(String text) throws TextException {
        ExpressionInterpreter interpreter = new ExpressionInterpreter(text);
        int value = interpreter.calculate();
        var component = new TextComposite(ComponentType.EXPRESSION);
        String[] characters = Integer.toString(value).split(SPLIT_REGEX);
        for (String ch : characters) {
            var symbolComponent = new SymbolLeaf(ComponentType.EXPRESSION_LEAF, ch.charAt(0));
            component.add(symbolComponent);
        }
        logger.log(Level.INFO, "Expression parsed successfully: " + text);
        return component;
    }
}
