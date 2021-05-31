package com.github.pluralia4j.dictionary;

import junit.framework.TestCase;

import static com.github.pluralia4j.dictionary.EnglishWordformsDictionary.INDEX_MANY;

public class EnglishWordformsDictionaryTest extends TestCase {

    public void testTranslateByRule(){
        final EnglishWordformsDictionary englishWordformsDictionary = new EnglishWordformsDictionary();
        assertEquals("dogs", englishWordformsDictionary.pluralByRule("dog", INDEX_MANY));
        assertEquals("boxes", englishWordformsDictionary.pluralByRule("box", INDEX_MANY));
        assertEquals("matches", englishWordformsDictionary.pluralByRule("match", INDEX_MANY));
        assertEquals("bosses", englishWordformsDictionary.pluralByRule("boss", INDEX_MANY));
        assertEquals("knives", englishWordformsDictionary.pluralByRule("knife", INDEX_MANY));
        assertEquals("kitties", englishWordformsDictionary.pluralByRule("kitty", INDEX_MANY));
        assertEquals("potatoes", englishWordformsDictionary.pluralByRule("potato", INDEX_MANY));
    }
}