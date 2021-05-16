package com.gorb.texthandling.parser;

import com.gorb.texthandling.entity.ComponentType;
import com.gorb.texthandling.entity.TextComponent;
import com.gorb.texthandling.entity.impl.CompositeLeaf;
import com.gorb.texthandling.entity.impl.TextComposite;
import com.gorb.texthandling.exception.TextException;
import com.gorb.texthandling.parser.impl.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class ParserTest {
    @DataProvider(name = "data")
    public Object[][] createData() throws TextException {
        TextComponent text = new TextComposite(ComponentType.TEXT);
        TextComponent paragraph = new TextComposite(ComponentType.PARAGRAPH);
        TextComponent sentence = new TextComposite(ComponentType.SENTENCE);
        TextComponent lexeme1 = new TextComposite(ComponentType.LEXEME);
        TextComponent lexeme2 = new TextComposite(ComponentType.LEXEME);
        TextComponent word = new TextComposite(ComponentType.WORD);
        TextComponent expression = new TextComposite(ComponentType.EXPRESSION);

        word.add(new CompositeLeaf(ComponentType.SYMBOL_LEAF, 'w'));
        word.add(new CompositeLeaf(ComponentType.SYMBOL_LEAF, 'o'));
        word.add(new CompositeLeaf(ComponentType.SYMBOL_LEAF, 'r'));
        word.add(new CompositeLeaf(ComponentType.SYMBOL_LEAF, 'd'));

        expression.add(new CompositeLeaf(ComponentType.EXPRESSION_LEAF, '%'));
        expression.add(new CompositeLeaf(ComponentType.EXPRESSION_LEAF, '%'));
        expression.add(new CompositeLeaf(ComponentType.EXPRESSION_LEAF, '%'));
        expression.add(new CompositeLeaf(ComponentType.EXPRESSION_LEAF, '%'));

        lexeme1.add(word);

        lexeme2.add(expression);
        lexeme2.add(new CompositeLeaf(ComponentType.PUNCTUATION_LEAF, '.'));

        sentence.add(lexeme1);
        sentence.add(lexeme1);
        sentence.add(lexeme1);
        sentence.add(lexeme2);

        paragraph.add(sentence);
        paragraph.add(sentence);

        text.add(paragraph);
        text.add(paragraph);

        return new Object[][]{
                {text, "\tword word word %%%%. word word word %%%%.\n\tword word word %%%%. word word word %%%%."}
        };
    }

    @Test(dataProvider = "data")
    public void testParser(TextComponent expected, String text) throws TextException {
        WordParser wordParser = new WordParser();
        ExpressionParser expressionParser = new ExpressionParser();
        LexemeParser lexemeParser = new LexemeParser(wordParser, expressionParser);
        SentenceParser sentenceParser = new SentenceParser(lexemeParser);
        ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);
        TextParser textParser = new TextParser(paragraphParser);
        TextComponent textComposite = textParser.parse(text);
        assertEquals(expected,textComposite);
    }
}
