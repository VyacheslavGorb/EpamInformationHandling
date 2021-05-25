package com.gorb.texthandling.service;

import com.gorb.texthandling.entity.InformationComponent;
import com.gorb.texthandling.exception.TextException;

import java.util.List;

public interface TextService {
    void sortParagraphsBySentenceCount(InformationComponent component) throws TextException;

    List<InformationComponent> findSentencesContainingLongestWord(InformationComponent component) throws TextException;

    void removeSentencesContainingLessWords(InformationComponent component, int wordCount) throws TextException;

    int countEqualWords(InformationComponent component, String wordToSearchFor) throws TextException;

    int countVowels(InformationComponent component) throws TextException;

    int countConsonants(InformationComponent component) throws TextException;
}
