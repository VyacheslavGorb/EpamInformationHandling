package com.gorb.texthandling.parser.impl;

import com.gorb.texthandling.entity.ComponentType;
import com.gorb.texthandling.entity.TextComponent;
import com.gorb.texthandling.entity.impl.SymbolLeaf;
import com.gorb.texthandling.entity.impl.TextComposite;
import com.gorb.texthandling.exception.TextException;
import com.gorb.texthandling.parser.InformationParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WordParser implements InformationParser {
    private static final Logger logger = LogManager.getLogger();
    private static final String WORD_SPLIT_REGEX = "";

    @Override
    public TextComponent parse(String text) throws TextException {
        var component = new TextComposite(ComponentType.WORD);
        String[] characters = text.split(WORD_SPLIT_REGEX);
        for (String ch : characters) {
            var symbolComponent = new SymbolLeaf(ComponentType.SYMBOL_LEAF, ch.charAt(0));
            component.add(symbolComponent);
        }
        logger.log(Level.INFO, "Word parsed successfully");
        return component;
    }
}
