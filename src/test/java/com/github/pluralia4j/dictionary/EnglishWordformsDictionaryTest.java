package com.github.pluralia4j.dictionary;

import junit.framework.TestCase;

import static com.github.pluralia4j.dictionary.EnglishWordformsDictionary.INDEX_MANY;

public class EnglishWordformsDictionaryTest extends TestCase {

    public void testTranslateByRule(){
        final EnglishWordformsDictionary englishWordformsDictionary = new EnglishWordformsDictionary();
        assertEquals("dogs", englishWordformsDictionary.translateByRule("dog", INDEX_MANY));
        assertEquals("boxes", englishWordformsDictionary.translateByRule("box", INDEX_MANY));
        assertEquals("matches", englishWordformsDictionary.translateByRule("match", INDEX_MANY));
        assertEquals("bosses", englishWordformsDictionary.translateByRule("boss", INDEX_MANY));
        assertEquals("knives", englishWordformsDictionary.translateByRule("knife", INDEX_MANY));
        assertEquals("kitties", englishWordformsDictionary.translateByRule("kitty", INDEX_MANY));
        assertEquals("potatoes", englishWordformsDictionary.translateByRule("potato", INDEX_MANY));
    }
}