package com.gorb.texthandling.service.impl;

import com.gorb.texthandling.entity.ComponentType;
import com.gorb.texthandling.entity.TextComponent;
import com.gorb.texthandling.exception.TextException;
import com.gorb.texthandling.service.TextService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

public class TextServiceImpl implements TextService {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void sortParagraphsBySentenceCount(TextComponent component) throws TextException {
        if (component.getType() != ComponentType.TEXT) {
            logger.log(Level.ERROR, "Illegal component type: {}", component.getType());
            throw new TextException("Illegal component type: " + component.getType());
        }
        List<TextComponent> paragraphs = component.getChildren();
        Comparator<TextComponent> sentenceCountComparator = (o1, o2) -> {
            int sentenceCount1 = countWords(o1);
            int sentenceCount2 = countWords(o2);
            int result = 0;
            if (sentenceCount1 < sentenceCount2) {
                result = -1;
            } else if (sentenceCount1 > sentenceCount2) {
                result = 1;
            }
            return result;
        };
        paragraphs.sort(sentenceCountComparator);
    }



    @Override
    public void findSentenceContainingLongestWord(TextComponent component) {
        //TODO
    }

    @Override
    public void removeSentencesContainingLessWords(TextComponent component, int wordCount) {
        //TODO
    }

    @Override
    public int countEqualWords(TextComponent component) {
        //TODO
        return 0;
    }

    @Override
    public int countVowels() {
        //TODO
        return 0;
    }

    @Override
    public int countConsonants() {
        //TODO
        return 0;
    }


    private int countWords(TextComponent component){
        List<TextComponent> childComponents = component.getChildren();
        EnumSet<ComponentType> validTypes = EnumSet.range(ComponentType.TEXT, ComponentType.WORD);
        int count = 0;
        for(TextComponent childComponent: childComponents){
            ComponentType currentType = childComponent.getType();
            if(currentType == ComponentType.WORD){
                count++;
            }else if (validTypes.contains(currentType)){
                count += countWords(childComponent);
            }
        }
        return count;
    }
}
