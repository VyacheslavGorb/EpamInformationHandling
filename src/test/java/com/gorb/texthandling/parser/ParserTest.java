package com.gorb.texthandling.parser;

import com.gorb.texthandling.entity.ComponentType;
import com.gorb.texthandling.entity.InformationComponent;
import com.gorb.texthandling.entity.impl.SymbolLeaf;
import com.gorb.texthandling.entity.impl.TextComposite;
import com.gorb.texthandling.exception.TextException;
import com.gorb.texthandling.parser.impl.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class ParserTest {
    @DataProvider(name = "data")
    public Object[][] createData() throws TextException {
        InformationComponent text = new TextComposite(ComponentType.TEXT);
        InformationComponent paragraph = new TextComposite(ComponentType.PARAGRAPH);
        InformationComponent sentence = new TextComposite(ComponentType.SENTENCE);
        InformationComponent lexeme1 = new TextComposite(ComponentType.LEXEME);
        InformationComponent lexeme2 = new TextComposite(ComponentType.LEXEME);
        InformationComponent word = new TextComposite(ComponentType.WORD);
        InformationComponent expression = new TextComposite(ComponentType.EXPRESSION);

        word.add(new SymbolLeaf(ComponentType.SYMBOL_LEAF, 'w'));
        word.add(new SymbolLeaf(ComponentType.SYMBOL_LEAF, 'o'));
        word.add(new SymbolLeaf(ComponentType.SYMBOL_LEAF, 'r'));
        word.add(new SymbolLeaf(ComponentType.SYMBOL_LEAF, 'd'));

        expression.add(new SymbolLeaf(ComponentType.EXPRESSION_LEAF, '2'));

        lexeme1.add(word);

        lexeme2.add(expression);
        lexeme2.add(new SymbolLeaf(ComponentType.PUNCTUATION_LEAF, '.'));

        sentence.add(lexeme1);
        sentence.add(lexeme1);
        sentence.add(lexeme1);
        sentence.add(lexeme2);

        paragraph.add(sentence);
        paragraph.add(sentence);

        text.add(paragraph);
        text.add(paragraph);

        return new Object[][]{
                {text, "\tword word word 2|0. word word word 2|0.\n\tword word word 2|0. word word word 2|0."}
        };
    }

    @Test(dataProvider = "data")
    public void testParser(InformationComponent expected, String text) throws TextException {
        WordParser wordParser = new WordParser();
        ExpressionParser expressionParser = new ExpressionParser();
        LexemeParser lexemeParser = new LexemeParser(wordParser, expressionParser);
        SentenceParser sentenceParser = new SentenceParser(lexemeParser);
        ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);
        TextParser textParser = new TextParser(paragraphParser);
        InformationComponent textComposite = textParser.parse(text);
        assertEquals(expected, textComposite);
    }
}
