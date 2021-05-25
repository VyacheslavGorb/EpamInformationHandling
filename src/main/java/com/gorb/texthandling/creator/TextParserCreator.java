package com.gorb.texthandling.creator;

import com.gorb.texthandling.parser.InformationParser;
import com.gorb.texthandling.parser.impl.*;

public class TextParserCreator {
    private TextParserCreator() {
    }

    public static InformationParser createParser() {
        WordParser wordParser = new WordParser();
        ExpressionParser expressionParser = new ExpressionParser();
        LexemeParser lexemeParser = new LexemeParser(wordParser, expressionParser);
        SentenceParser sentenceParser = new SentenceParser(lexemeParser);
        ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);
        return new TextParser(paragraphParser);
    }
}
