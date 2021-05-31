package com.github.pluralia4j.dictionary;

import junit.framework.TestCase;

import static com.github.pluralia4j.dictionary.RussianWordformsDictionary.INDEX_FEW;

public class WordformsDictionaryTest extends TestCase {

    public void testPut(){
        WordformsDictionary wordformsDictionary = new WordformsDictionary();
        wordformsDictionary.put("собака", "собаки", "собак");
        wordformsDictionary.put("кот", "котей", "котешек");
        WordformsDictionary wordformsDictionaryFull = new WordformsDictionary();
        wordformsDictionaryFull.put("кот", "кота", "котов");
        wordformsDictionaryFull.put(wordformsDictionary);
        assertEquals("кота", wordformsDictionaryFull.pluralByDictionary("кот", INDEX_FEW));
        assertEquals("собаки", wordformsDictionaryFull.pluralByDictionary("собаки", INDEX_FEW));
    }

    public void testPutTop(){
        WordformsDictionary wordformsDictionary = new WordformsDictionary();
        wordformsDictionary.put("кот", "кота", "котов");
        WordformsDictionary wordformsDictionaryFull = new WordformsDictionary();
        wordformsDictionaryFull.put("кот", "котей", "котешек");
        wordformsDictionaryFull.putTop(wordformsDictionary);
        assertEquals("кота", wordformsDictionaryFull.pluralByDictionary("кот", INDEX_FEW));
    }

    public void testPluralByRule() {
        WordformsDictionary wordformsDictionary = new WordformsDictionary();
        // no language rules in default wordforms dictionary
        assertEquals("крыса", wordformsDictionary.pluralByRule("крыса", INDEX_FEW));
    }

    public void testPlural() {
        WordformsDictionary wordformsDictionary = new WordformsDictionary();
        wordformsDictionary.put("кот", "кота", "котов");
        assertEquals("кота", wordformsDictionary.plural("кот", INDEX_FEW));
        assertEquals("Кота", wordformsDictionary.plural("Кот", INDEX_FEW));
        assertEquals("КОТА", wordformsDictionary.plural("КОТ", INDEX_FEW));
        assertEquals("Кота", wordformsDictionary.plural("кОта", INDEX_FEW));
    }

    public void testRemove(){
        final WordformsDictionary wordformsDictionary = new WordformsDictionary();
        wordformsDictionary.put("кот", "котей", "котешек");
        final WordformsDictionary wordformsDictionaryFull = new WordformsDictionary();
        wordformsDictionaryFull.put("кот", "кота", "котов");
        wordformsDictionaryFull.putTop(wordformsDictionary);
        wordformsDictionaryFull.remove(wordformsDictionary);
        assertEquals("кота", wordformsDictionaryFull.pluralByDictionary("кот", INDEX_FEW));
    }
}