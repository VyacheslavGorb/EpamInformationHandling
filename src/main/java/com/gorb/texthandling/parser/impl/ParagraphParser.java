package com.gorb.texthandling.parser.impl;

import com.gorb.texthandling.entity.ComponentType;
import com.gorb.texthandling.entity.TextComponent;
import com.gorb.texthandling.entity.impl.TextComposite;
import com.gorb.texthandling.exception.TextException;
import com.gorb.texthandling.parser.InformationParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParagraphParser implements InformationParser {
    private static final Logger logger = LogManager.getLogger();
    private static final String PARAGRAPH_SPLIT_REGEX = "((?<=[?!]|((?<!\\.)\\.(?!\\.))|(\\.\\.\\.)))\\s+";
    private InformationParser nextParser;

    public ParagraphParser(InformationParser nextParser) {
        this.nextParser = nextParser;
    }

    @Override
    public TextComponent parse(String text) throws TextException {
        if (nextParser == null) {
            logger.log(Level.ERROR, "Sentence parser is not specified");
            throw new TextException("Sentence parser is not specified");
        }
        String[] sentences = text.split(PARAGRAPH_SPLIT_REGEX);
        var component = new TextComposite(ComponentType.PARAGRAPH);
        for (String sentence : sentences) {
            TextComponent sentenceComponent = nextParser.parse(sentence);
            component.add(sentenceComponent);
        }
        logger.log(Level.INFO, "Paragraph parsed successfully");
        return component;
    }
}
