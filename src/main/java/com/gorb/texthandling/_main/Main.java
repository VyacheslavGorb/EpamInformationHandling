package com.gorb.texthandling._main;

import com.gorb.texthandling.entity.TextComponent;
import com.gorb.texthandling.exception.TextException;
import com.gorb.texthandling.parser.impl.*;
import com.gorb.texthandling.service.TextService;
import com.gorb.texthandling.service.impl.TextServiceImpl;

public class Main {
    public static void main(String[] args) throws TextException {
//        String text = "(five)";
//        var res = text.split("(?=([)?,!]|((?<!\\.)\\.(?!\\.))|(\\.{3}))$)|(?<=^\\()");
//        for (int i = 0; i < res.length; i++) {
//            System.out.print('$');
//            System.out.print(res[i]);
//            System.out.println('$');
//        }

        String text =
                "  kldd fss sss sss sss ssss ssss sss sss ssssss ssss ssssss sssss sss ssssss sssss ss s sssss ss sssss ss ssssss sssssss sssdd\n" +
                        "  It has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the “Динамо” (Рига) with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker Faclon9 including versions of Lorem Ipsum!\n" +
                        "  It is a long a!=b established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution ob.toString(a?b:c), as opposed to using Content here, content here's, making it look like readable English?\n" +
                        "  It is a established fact that a reader will be of a page when looking at its layout...\n" +
                        "  Bye бандерлоги.\n" +
                        "  a a a a\n" +
                        "  a a a\n" +
                        "  a a\n";

//        var leaf = new CompositeLeaf(ComponentType.TEXT,'d');
        WordParser wordParser = new WordParser();
        ExpressionParser expressionParser = new ExpressionParser();
        LexemeParser lexemeParser = new LexemeParser(wordParser, expressionParser);
        SentenceParser sentenceParser = new SentenceParser(lexemeParser);
        ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);
        TextParser textParser = new TextParser(paragraphParser);
        TextComponent textComposite = textParser.parse(text);

        System.out.println(textComposite);

        TextService service = new TextServiceImpl();
        service.removeSentencesContainingLessWords(textComposite, 4);

        System.out.println(textComposite);


//        lexemeParser.parse("more-or-less");
    }
}
