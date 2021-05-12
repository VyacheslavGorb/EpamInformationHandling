package com.gorb.texthandling.parser.impl;

import com.gorb.texthandling.entity.ComponentType;
import com.gorb.texthandling.entity.TextComponent;
import com.gorb.texthandling.entity.impl.TextComposite;
import com.gorb.texthandling.exception.TextException;
import com.gorb.texthandling.parser.InformationParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SentenceParser implements InformationParser {
    private static final Logger logger = LogManager.getLogger();
    private static final String SENTENCE_SPLIT_REGEX = "\\s+";
    private InformationParser nextParser;

    public SentenceParser(InformationParser nextParser) {
        this.nextParser = nextParser;
    }

    @Override
    public TextComponent parse(String text) throws TextException {
        if (nextParser == null) {
            logger.log(Level.ERROR, "Lexeme parser is not specified");
            throw new TextException("Lexeme parser is not specified");
        }
        String[] lexemes = text.split(SENTENCE_SPLIT_REGEX);
        var component = new TextComposite(ComponentType.SENTENCE);
        for (String lexeme : lexemes) {
            TextComponent lexemeComponent = nextParser.parse(lexeme);
            component.add(lexemeComponent);
        }
        logger.log(Level.INFO, "Sentence parsed successfully");
        return component;
    }
}
