package com.gorb.texthandling._main;

import com.gorb.texthandling.creator.TextParserCreator;
import com.gorb.texthandling.entity.TextComponent;
import com.gorb.texthandling.exception.TextException;
import com.gorb.texthandling.parser.InformationParser;
import com.gorb.texthandling.reader.TextFileReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.net.URL;
import java.util.List;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        URL fileURL = Main.class.getClassLoader().getResource("files/content.txt");
        if (fileURL == null) {
            logger.log(Level.FATAL, "File does not exist");
            return;
        }
        File file = new File(fileURL.getFile());
        String filePath = file.getAbsolutePath();
        try {
            TextFileReader reader = new TextFileReader();
            List<String> lines = reader.readFile(filePath);
            InformationParser textParser = TextParserCreator.createParser();
            String text = String.join("\n", lines);
            System.out.println("text = " + text);
            TextComponent textComponent = textParser.parse(text);
            System.out.println("textComponent = \n" + textComponent);
        } catch (TextException e) {
            logger.log(Level.FATAL, e.getMessage());
        }
    }
}
