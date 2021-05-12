package com.gorb.texthandling.parser.impl;

import com.gorb.texthandling.entity.ComponentType;
import com.gorb.texthandling.entity.TextComponent;
import com.gorb.texthandling.entity.impl.TextComposite;
import com.gorb.texthandling.exception.TextException;
import com.gorb.texthandling.parser.InformationParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextParser implements InformationParser {
    private static final Logger logger = LogManager.getLogger();
    private static final String TEXT_SPLIT_REGEX = "\\n+[\\s]*";
    private InformationParser nextParser;

    public TextParser(InformationParser nextParser) {
        this.nextParser = nextParser;
    }

    @Override
    public TextComponent parse(String text) throws TextException {
        if (nextParser == null) {
            logger.log(Level.ERROR, "Paragraph parser is not specified");
            throw new TextException("Paragraph parser is not specified");
        }

        String[] paragraphs = text.strip().split(TEXT_SPLIT_REGEX);
        var component = new TextComposite(ComponentType.TEXT);
        for (String paragraph : paragraphs) {
            TextComponent paragraphComponent = nextParser.parse(paragraph);
            component.add(paragraphComponent);
        }
        logger.log(Level.INFO, "Text parsed successfully");
        return component;
    }
}
