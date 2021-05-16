package com.gorb.texthandling._main;

import com.gorb.texthandling.creator.TextParserCreator;
import com.gorb.texthandling.entity.TextComponent;
import com.gorb.texthandling.exception.TextException;
import com.gorb.texthandling.parser.InformationParser;

public class Main {
    public static void main(String[] args) throws TextException {
        String text =
                "  kldd fss sss sss sss ssss ssss sss sss ssssss ssss ssssss sssss sss ssssss sssss ss s sssss ss sssss ss ssssss sssssss sssdd\n" +
                        "  It has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the “Динамо” (Рига) with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker Faclon9 including versions of Lorem Ipsum!\n" +
                        "  It is a long a!=b established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution ob.toString(a?b:c), as opposed to using Content here, content here's, making it look like readable English?\n" +
                        "  It is a established fact that a reader will be of a page when looking at its layout...\n" +
                        "  Bye бандерлоги.\n" +
                        "  a a a a\n" +
                        "  a a a\n" +
                        "  a a\n";

        InformationParser textParser = TextParserCreator.createParser();
        TextComponent textComposite = textParser.parse(text);
        System.out.println(textComposite);
    }
}
