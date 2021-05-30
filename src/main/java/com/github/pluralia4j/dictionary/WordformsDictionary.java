package com.github.pluralia4j.dictionary;

import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.github.pluralia4j.dictionary.WordformsUtils.caseFormatByWord;
import static com.github.pluralia4j.dictionary.WordformsUtils.getOrLast;

public class WordformsDictionary {
    @Getter private final Multimap<String, String> localDictionary;

    private final List<Multimap<String, String>> dictionaries = new ArrayList<>();

    /**
     *
     */
    public WordformsDictionary() {
        localDictionary = MultimapBuilder.hashKeys().arrayListValues().build();
        dictionaries.add(localDictionary);
    }

    /**
     *
     * @param localDictionary
     */
    public WordformsDictionary(Multimap<String, String> localDictionary) {
        this.localDictionary = localDictionary;
        dictionaries.add(localDictionary);
    }

    /**
     *
     * @param wordformsDictionary
     * @return
     */
    public WordformsDictionary put(WordformsDictionary wordformsDictionary) {
        dictionaries.add(wordformsDictionary.localDictionary);
        return this;
    }

    /**
     *
     * @param additionalDictionary
     * @return
     */
    public WordformsDictionary put(Multimap<String, String> additionalDictionary) {
        dictionaries.add(additionalDictionary);
        return this;
    }

    /**
     *
     * @param wordformsDictionary
     * @return
     */
    public WordformsDictionary putTop(WordformsDictionary wordformsDictionary) {
        dictionaries.add(0, wordformsDictionary.localDictionary);
        return this;
    }

    public WordformsDictionary put(String key, String... wordforms) {
        List<String> wordformsList = Lists.newArrayList(wordforms);
        wordformsList.add(0, key);
        localDictionary.putAll(key, wordformsList);
        return this;
    }


    private String findByKey(@NotNull String key, int index) {
        for (Multimap<String, String> dictionary : dictionaries) {
            if(dictionary.containsKey(key)) {
                List<String> wordforms = new ArrayList<>(dictionary.get(key));
                return getOrLast(wordforms, index);
            }
        }
        return null;
    }

    /**
     *
     * @param key
     * @param index
     * @return
     */
    public String translateByRule(@NotNull String key, int index) {
        return key;
    }

    /**
     *
     * @param key
     * @param index
     * @return
     */
    protected String translateWordform(@NotNull String key, int index) {
        final String wordform = findByKey(key, index);
        if(Objects.nonNull(wordform)) {
            return wordform;
        }
        return translateByRule(key, index);
    }

    /**
     *
     * @param word
     * @return
     */
    public String translate(@NotNull String word, int index) {
        final WordCase wordCase = caseFormatByWord(word);
        final String key = word.toLowerCase();
        final String wordform = translateWordform(key, index);
        return caseFormatByWord(wordform, wordCase);
    }

    public void remove(WordformsDictionary messageTemplateWordforms) {
        dictionaries.remove(messageTemplateWordforms);
    }
}
