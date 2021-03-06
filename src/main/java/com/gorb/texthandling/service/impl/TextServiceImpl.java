package com.gorb.texthandling.service.impl;

import com.gorb.texthandling.entity.ComponentType;
import com.gorb.texthandling.entity.InformationComponent;
import com.gorb.texthandling.exception.TextException;
import com.gorb.texthandling.service.TextService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TextServiceImpl implements TextService {
    private static final Logger logger = LogManager.getLogger();
    private static final String VOWEL_REGEX = "[AEIOUaeiou]";
    private static final String CONSONANT_REGEX = "[[^AEIOUaeiou]&&A-Za-z]";

    @Override
    public void sortParagraphsBySentenceCount(InformationComponent component) throws TextException {
        if (component.getType() != ComponentType.TEXT) {
            logger.log(Level.ERROR, "Illegal component type: {}. Expected TEXT.", component.getType());
            throw new TextException("Illegal component type: . Expected TEXT." + component.getType());
        }
        Comparator<InformationComponent> sentenceCountComparator = (o1, o2) -> {
            int count1 = o1.getChildren().size();
            int count2 = o2.getChildren().size();
            return Integer.compare(count1, count2);
        };
        List<InformationComponent> paragraphs = component.getChildren();
        paragraphs.sort(sentenceCountComparator);
    }

    @Override
    public void removeSentencesContainingLessWords(InformationComponent component, int minimalWordCount)
            throws TextException {
        if (component.getType() != ComponentType.TEXT) {
            logger.log(Level.ERROR, "Illegal component type: {}. Expected TEXT.", component.getType());
            throw new TextException("Illegal component type: . Expected TEXT." + component.getType());
        }

        List<InformationComponent> paragraphs = component.getChildren();
        for (InformationComponent paragraph : paragraphs) {
            List<InformationComponent> sentences = paragraph.getChildren();
            sentences.removeIf(sentence -> countWords(sentence) < minimalWordCount);
        }
    }

    @Override
    public List<InformationComponent> findSentencesContainingLongestWord(InformationComponent component) throws TextException {
        if (component.getType() != ComponentType.TEXT) {
            logger.log(Level.ERROR, "Illegal component type: {}. Expected TEXT.", component.getType());
            throw new TextException("Illegal component type: . Expected TEXT." + component.getType());
        }

        Optional<Integer> maxLength = component.getChildren().stream()
                .flatMap(paragraph -> paragraph.getChildren().stream())
                .map(this::countMaximalWordLength)
                .max(Integer::compareTo);

        if (maxLength.isEmpty()) {
            logger.log(Level.ERROR, "Component contains no words");
            throw new TextException("Component contains no words");
        }

        return component.getChildren().stream()
                .flatMap(paragraph -> paragraph.getChildren().stream())
                .filter(sentence -> countMaximalWordLength(sentence) == maxLength.get())
                .collect(Collectors.toList());
    }

    @Override
    public int countEqualWords(InformationComponent component, String wordToSearchFor) throws TextException {
        if (component.getType() != ComponentType.TEXT) {
            logger.log(Level.ERROR, "Illegal component type: {}. Expected TEXT.", component.getType());
            throw new TextException("Illegal component type: . Expected TEXT." + component.getType());
        }
        return (int) component.getChildren().stream()
                .flatMap(paragraph -> paragraph.getChildren().stream())
                .flatMap(sentence -> sentence.getChildren().stream())
                .flatMap(lexeme -> lexeme.getChildren().stream())
                .filter(el -> el.getType() == ComponentType.WORD)
                .filter(word -> word.toString().equals(wordToSearchFor))
                .count();
    }

    @Override
    public int countVowels(InformationComponent component) throws TextException {
        if (component.getType() != ComponentType.SENTENCE) {
            logger.log(Level.ERROR, "Illegal component type: {}. Expected SENTENCE.", component.getType());
            throw new TextException("Illegal component type: . Expected SENTENCE." + component.getType());
        }
        return countLetters(component, VOWEL_REGEX);
    }

    @Override
    public int countConsonants(InformationComponent component) throws TextException {
        if (component.getType() != ComponentType.SENTENCE) {
            logger.log(Level.ERROR, "Illegal component type: {}. Expected SENTENCE.", component.getType());
            throw new TextException("Illegal component type: . Expected SENTENCE." + component.getType());
        }
        return countLetters(component, CONSONANT_REGEX);
    }

    private int countLetters(InformationComponent component, String regex) {
        long count = component.getChildren().stream()
                .flatMap(lexeme -> lexeme.getChildren().stream())
                .filter(el -> el.getType() == ComponentType.WORD)
                .flatMap(word -> word.getChildren().stream())
                .filter(el -> el.toString().matches(regex))
                .count();
        return (int) count;
    }

    private int countWords(InformationComponent component) {
        List<InformationComponent> childComponents = component.getChildren();
        int count = 0;
        EnumSet<ComponentType> typesContainingWords = EnumSet.range(ComponentType.TEXT, ComponentType.LEXEME);
        for (InformationComponent childComponent : childComponents) {
            ComponentType currentType = childComponent.getType();
            if (currentType == ComponentType.WORD) {
                count++;
            } else if (typesContainingWords.contains(currentType)) {
                count += countWords(childComponent);
            }
        }
        return count;
    }

    private int countMaximalWordLength(InformationComponent sentence) {
        return sentence.getChildren().stream()
                .flatMap(lexeme -> lexeme.getChildren().stream())
                .filter(el -> el.getType() == ComponentType.WORD)
                .map(el -> el.getChildren().size())
                .max(Integer::compareTo)
                .orElse(-1);
    }
}
