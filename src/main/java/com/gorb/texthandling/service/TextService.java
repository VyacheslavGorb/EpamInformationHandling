package com.gorb.texthandling.service;

import com.gorb.texthandling.entity.TextComponent;
import com.gorb.texthandling.exception.TextException;

import java.util.List;

public interface TextService {
    void sortParagraphsBySentenceCount(TextComponent component) throws TextException;

    List<TextComponent> findSentencesContainingLongestWord(TextComponent component) throws TextException;

    void removeSentencesContainingLessWords(TextComponent component, int wordCount) throws TextException;

    int countEqualWords(TextComponent component);

    int countVowels(TextComponent component) throws TextException;

    int countConsonants(TextComponent component) throws TextException;
}
