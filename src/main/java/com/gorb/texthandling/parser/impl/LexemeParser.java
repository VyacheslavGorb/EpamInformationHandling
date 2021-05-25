package com.gorb.texthandling.parser.impl;

import com.gorb.texthandling.entity.ComponentType;
import com.gorb.texthandling.entity.InformationComponent;
import com.gorb.texthandling.entity.impl.SymbolLeaf;
import com.gorb.texthandling.entity.impl.TextComposite;
import com.gorb.texthandling.exception.TextException;
import com.gorb.texthandling.parser.InformationParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LexemeParser implements InformationParser {
    private static final Logger logger = LogManager.getLogger();
    private static final String LEXEME_SPLIT_REGEX = "(?=([?,!-]|((?<!\\.)\\.(?!\\.))|(\\.{3}))$)|((?<=^\\()(?![0-9~]))|((?<=[a-zA-Z])(?=\\)))";
    private static final String WORD_REGEX = "[а-яА-Я\\w'-]+";
    private static final String PUNCTUATION_REGEX = "[?.,!)(-]|((?<!\\.)\\.(?!\\.))|(\\.{3})";
    private InformationParser wordParser;
    private InformationParser expressionParser;

    public LexemeParser(InformationParser wordParser, InformationParser expressionParser) {
        this.wordParser = wordParser;
        this.expressionParser = expressionParser;
    }

    @Override
    public InformationComponent parse(String text) throws TextException {
        if (wordParser == null || expressionParser == null) {
            logger.log(Level.ERROR, "At least one of parsers is not specified");
            throw new TextException("At least one of parsers is not specified");
        }

        String[] lexemes = text.split(LEXEME_SPLIT_REGEX);
        var component = new TextComposite(ComponentType.LEXEME);
        for (String lexeme : lexemes) {
            InformationComponent currentComponent;
            if (lexeme.matches(WORD_REGEX)) {
                currentComponent = wordParser.parse(lexeme);
            } else if (lexeme.matches(WORD_REGEX) && lexeme.length() == 1) {
                currentComponent = new SymbolLeaf(ComponentType.SYMBOL_LEAF, lexeme.charAt(0));
            } else if (lexeme.matches(PUNCTUATION_REGEX)) {
                currentComponent = new SymbolLeaf(ComponentType.PUNCTUATION_LEAF, lexeme.charAt(0));
            } else {
                currentComponent = expressionParser.parse(lexeme);
            }
            component.add(currentComponent);
        }
        logger.log(Level.INFO, "Lexeme parsed successfully");
        return component;
    }
}
