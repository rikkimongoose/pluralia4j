package com.github.pluralia4j.dictionary;

import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.github.pluralia4j.dictionary.WordformsUtils.getOrLast;

/**
 * Wordforms storage
 */
public class WordformsDictionary {
    /**
     * Single index
     */
    public static final int INDEX_ONE = 0;

    /**
     * Local dictionary of storage
     */
    @Getter private final Multimap<String, String> localDictionary;

    /**
     * Dictionary collections stored in current wordforms dictionary
     */
    private final List<Multimap<String, String>> dictionaries = new ArrayList<>();

    /**
     * Constructor
     */
    public WordformsDictionary() {
        localDictionary = MultimapBuilder.hashKeys().arrayListValues().build();
        dictionaries.add(localDictionary);
    }

    /**
     * Parametrised constructor with predefined wordforms
     * @param localDictionary predefined wordforms
     */
    public WordformsDictionary(Multimap<String, String> localDictionary) {
        this.localDictionary = localDictionary;
        dictionaries.add(localDictionary);
    }

    /**
     * Put a dictionary to the dictionaries list
     * @param wordformsDictionary dictionary to add
     * @return chain of initialisation
     */
    public WordformsDictionary put(WordformsDictionary wordformsDictionary) {
        dictionaries.add(wordformsDictionary.localDictionary);
        return this;
    }

    /**
     * Put a dictionary to the top of dictionaries list
     *
     * @param wordformsDictionary dictionary to add
     * @return chain of initialisation
     */
    public WordformsDictionary putTop(WordformsDictionary wordformsDictionary) {
        if(!dictionaries.contains(wordformsDictionary)) {
            dictionaries.add(0, wordformsDictionary.localDictionary);
        }
        return this;
    }

    /**
     * Add a new word to plural forms dictionary
     * @param word to add to plural forms dictionary
     * @param wordforms plural wordorms
     * @return chain of initialisation
     */
    public WordformsDictionary put(String word, String... wordforms) {
        List<String> wordformsList = Lists.newArrayList(wordforms);
        wordformsList.add(0, word);
        if(localDictionary.containsKey(word)) {
            localDictionary.get(word).clear();
        }
        localDictionary.putAll(word, wordformsList.stream().map(String::toLowerCase).collect(Collectors.toList()));
        return this;
    }

    /**
     *
     * @param key
     * @param index
     * @return
     */
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
        final WordCase wordCase = WordformsUtils.wordCaseByWord(word);
        final String key = word.toLowerCase();
        final String wordform = translateWordform(key, index);
        return WordformsUtils.wordToWordCase(wordform, wordCase);
    }

    /**
     *
     * @param messageTemplateWordforms
     */
    public void remove(WordformsDictionary messageTemplateWordforms) {
        dictionaries.remove(messageTemplateWordforms);
    }
}
