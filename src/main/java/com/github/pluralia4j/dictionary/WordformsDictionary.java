package com.github.pluralia4j.dictionary;

import com.github.pluralia4j.lang.PluralType;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import lombok.Getter;
import lombok.NonNull;
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
    public WordformsDictionary putTop(@NonNull WordformsDictionary wordformsDictionary) {
        if(!dictionaries.contains(wordformsDictionary.localDictionary)) {
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
     * Find wordforms by word
     *
     * @param word       word to pluralise
     * @param pluralType plural wordform
     * @return word in plural form
     */
    private String findByWord(@NotNull String word, PluralType pluralType) {
        return dictionaries.stream()
                .filter(dictionary -> dictionary.containsKey(word))
                .map(dictionary -> getOrLast(new ArrayList<>(dictionary.get(word)), pluralType.getIndex()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Get plural form of a word according to grammar rules, without using any wordforms dictionaries.
     *
     * @param word       word to pluralise
     * @param pluralType plural wordform's
     * @return word in plural form
     */
    public String pluralByRule(@NotNull String word, PluralType pluralType) {
        return word;
    }

    /**
     * Get plural wordform according to local dictionaries
     *
     * @param word       word to pluralise
     * @param pluralType plural wordform
     * @return word in plural form
     */
    protected String pluralByDictionary(@NotNull String word, PluralType pluralType) {
        final String wordform = findByWord(word, pluralType);
        if (Objects.nonNull(wordform)) {
            return wordform;
        }
        return pluralByRule(word, pluralType);
    }

    /**
     * Get plural wordform for a word in same case
     *
     * @param word       word to pluralise
     * @param pluralType plural wordform index
     * @return word in plural form
     */
    public String plural(@NotNull String word, PluralType pluralType) {
        final WordCase wordCase = WordformsUtils.wordCaseByWord(word);
        final String key = word.toLowerCase();
        final String wordform = pluralByDictionary(key, pluralType);
        return WordformsUtils.wordToWordCase(wordform, wordCase);
    }

    /**
     * Remove an additional WordformsDictionary
     * @param wordformsDictionary WordformsDictionary to remove
     */
    public void remove(WordformsDictionary wordformsDictionary) {
        dictionaries.remove(wordformsDictionary.getLocalDictionary());
    }
}
