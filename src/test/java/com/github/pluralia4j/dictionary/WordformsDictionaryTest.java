package com.github.pluralia4j.dictionary;

import com.github.pluralia4j.lang.PluralType;
import junit.framework.TestCase;

public class WordformsDictionaryTest extends TestCase {

    public void testPut() {
        WordformsDictionary wordformsDictionary = new WordformsDictionary();
        wordformsDictionary.put("собака", "собаки", "собак");
        wordformsDictionary.put("кот", "котей", "котешек");
        WordformsDictionary wordformsDictionaryFull = new WordformsDictionary();
        wordformsDictionaryFull.put("кот", "кота", "котов");
        wordformsDictionaryFull.put(wordformsDictionary);
        assertEquals("кота", wordformsDictionaryFull.pluralByDictionary("кот", PluralType.OTHER));
        assertEquals("собаки", wordformsDictionaryFull.pluralByDictionary("собаки", PluralType.OTHER));
    }

    public void testPutTop(){
        WordformsDictionary wordformsDictionary = new WordformsDictionary();
        wordformsDictionary.put("кот", "кота", "котов");
        WordformsDictionary wordformsDictionaryFull = new WordformsDictionary();
        wordformsDictionaryFull.put("кот", "котей", "котешек");
        wordformsDictionaryFull.putTop(wordformsDictionary);
        assertEquals("кота", wordformsDictionaryFull.pluralByDictionary("кот", PluralType.OTHER));
    }

    public void testPluralByRule() {
        WordformsDictionary wordformsDictionary = new WordformsDictionary();
        // no language rules in default wordforms dictionary
        assertEquals("крыса", wordformsDictionary.pluralByRule("крыса", PluralType.OTHER));
    }

    public void testPlural() {
        WordformsDictionary wordformsDictionary = new WordformsDictionary();
        wordformsDictionary.put("кот", "котов", "кота");
        assertEquals("кота", wordformsDictionary.plural("кот", PluralType.FEW));
        assertEquals("Кота", wordformsDictionary.plural("Кот", PluralType.FEW));
        assertEquals("КОТА", wordformsDictionary.plural("КОТ", PluralType.FEW));
        assertEquals("Кота", wordformsDictionary.plural("кОта", PluralType.FEW));
    }

    public void testRemove(){
        final WordformsDictionary wordformsDictionary = new WordformsDictionary();
        wordformsDictionary.put("кот", "котей", "котешек");
        final WordformsDictionary wordformsDictionaryFull = new WordformsDictionary();
        wordformsDictionaryFull.put("кот", "котов", "кота");
        wordformsDictionaryFull.putTop(wordformsDictionary);
        wordformsDictionaryFull.remove(wordformsDictionary);
        assertEquals("кота", wordformsDictionaryFull.pluralByDictionary("кот", PluralType.FEW));
    }
}