package com.github.pluralia4j.dictionary;

import junit.framework.TestCase;

public class WordformsDictionaryTest extends TestCase {

    public void testPut(){
        WordformsDictionary wordformsDictionary = new WordformsDictionary();
        wordformsDictionary.put("кот", "кота", "котов");
        WordformsDictionary wordformsDictionaryFull = new WordformsDictionary();
        wordformsDictionary.put(wordformsDictionary);
        wordformsDictionaryFull.getLocalDictionary().containsKey("кот");
    }

}