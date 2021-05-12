package com.gorb.texthandling.service;

import com.gorb.texthandling.entity.TextComponent;
import com.gorb.texthandling.exception.TextException;

public interface TextService {
    void sortParagraphsBySentenceCount(TextComponent component) throws TextException;

    void findSentenceContainingLongestWord(TextComponent component);

    void removeSentencesContainingLessWords(TextComponent component, int wordCount);

    int countEqualWords(TextComponent component);

    int countVowels();

    int countConsonants();
}
