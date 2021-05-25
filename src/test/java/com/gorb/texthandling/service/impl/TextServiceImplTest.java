package com.gorb.texthandling.service.impl;

import com.gorb.texthandling.creator.TextParserCreator;
import com.gorb.texthandling.entity.TextComponent;
import com.gorb.texthandling.exception.TextException;
import com.gorb.texthandling.parser.InformationParser;
import com.gorb.texthandling.parser.impl.ExpressionParser;
import com.gorb.texthandling.parser.impl.LexemeParser;
import com.gorb.texthandling.parser.impl.SentenceParser;
import com.gorb.texthandling.parser.impl.WordParser;
import com.gorb.texthandling.service.TextService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class TextServiceImplTest {

    private InformationParser textParser;
    private InformationParser sentenceParser;
    private TextService service;


    @BeforeClass
    public void createParser() {
        textParser = TextParserCreator.createParser();
        service = new TextServiceImpl();
        WordParser wordParser = new WordParser();
        ExpressionParser expressionParser = new ExpressionParser();
        LexemeParser lexemeParser = new LexemeParser(wordParser, expressionParser);
        sentenceParser = new SentenceParser(lexemeParser);
    }

    @DataProvider(name = "dataParagraph")
    public Object[][] createParagraphData() throws TextException {
        String str1 = "\tSentence1.\n\tSentence1. Sentence2.\n\tSentence1. Sentence2. Sentence3\n";
        String str2 = "\tSentence1. Sentence2.\n\tSentence1.\n\tSentence1. Sentence2. Sentence3\n";
        return new Object[][]{
                {textParser.parse(str1), textParser.parse(str2)}
        };
    }

    @Test(dataProvider = "dataParagraph")
    public void sortParagraphsBySentenceCountTest(TextComponent expected, TextComponent component) throws TextException {
        service.sortParagraphsBySentenceCount(component);
        assertEquals(component, expected);
    }


    @DataProvider(name = "dataEqualWords")
    public Object[][] createEqualWordData() throws TextException {
        String str1 = "\tWord Word Word Word f.";
        return new Object[][]{
                {4, textParser.parse(str1), "Word"}
        };
    }

    @Test(dataProvider = "dataEqualWords")
    public void countEqualWordsTest(Integer expected, TextComponent component, String word) throws TextException {
        Integer count = service.countEqualWords(component, word);
        assertEquals(expected, count);
    }

    @DataProvider(name = "dataVowels")
    public Object[][] vowelsData() throws TextException {
        String sentence1 = "Hello world.";
        String sentence2 = "Hello worldooo.";
        return new Object[][]{
                {3, sentenceParser.parse(sentence1)},
                {6, sentenceParser.parse(sentence2)}
        };
    }

    @Test(dataProvider = "dataVowels")
    public void countVowelsTest(Integer expected, TextComponent component) throws TextException {
        Integer res = service.countVowels(component);
        assertEquals(expected, res);
    }


    @DataProvider(name = "dataConsonants")
    public Object[][] consonantsData() throws TextException {
        String sentence1 = "Hello world.";
        String sentence2 = "Helllo worldooo.";
        return new Object[][]{
                {7, sentenceParser.parse(sentence1)},
                {8, sentenceParser.parse(sentence2)}
        };
    }

    @Test(dataProvider = "dataConsonants")
    public void countConsonantsTest(Integer expected, TextComponent component) throws TextException {
        Integer res = service.countConsonants(component);
        assertEquals(expected, res);
    }

    @DataProvider(name = "dataLongestWord")
    public Object[][] createLongestWordData() throws TextException {
        TextComponent sentence1 = sentenceParser.parse("Seentence3.");
        TextComponent sentence2 = sentenceParser.parse("Seentence2.");
        TextComponent textComponent1 = textParser.parse("Sentence1. Seentence3. Seentence2.");
        List<TextComponent> sentences = List.of(sentence1, sentence2);

        return new Object[][]{
                {textComponent1, sentences}
        };
    }

    @Test(dataProvider = "dataLongestWord")
    public void findSentencesContainingLongestWordTest(TextComponent component, List<TextComponent> expected)
            throws TextException {
        List<TextComponent> list = service.findSentencesContainingLongestWord(component);
        assertEquals(expected, list);
    }


    @DataProvider(name = "dataLessWords")
    public Object[][] createLessWordsData() throws TextException {
        TextComponent textComponent1 = textParser.parse("Sentence1 w f. Seentence3 r. Seentence2 rt t.");
        TextComponent textComponent2 = textParser.parse("Sentence1 w f. Seentence2 rt t.");
        return new Object[][]{
                {textComponent1, 3, textComponent2}
        };
    }

    @Test(dataProvider = "dataLessWords")
    public void removeSentencesContainingLessWords(TextComponent component, int count, TextComponent expected)
            throws TextException {
        service.removeSentencesContainingLessWords(component, count);
        assertEquals(expected, component);
    }


}
