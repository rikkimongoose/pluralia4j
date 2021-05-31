package com.github.pluralia4j.dictionary;

import junit.framework.TestCase;

public class WordformsDictionaryLoaderTest extends TestCase {

    public void testLoadRussianDictionary() {
        WordformsDictionary wordformsDictionary = WordformsDictionaryLoader.getRussianDictionary();
        assertFalse(wordformsDictionary.getLocalDictionary().isEmpty());
    }

    public void testLoadEnglishDictionary() {
        WordformsDictionary wordformsDictionary = WordformsDictionaryLoader.getRussianDictionary();
        assertFalse(wordformsDictionary.getLocalDictionary().isEmpty());
    }
}