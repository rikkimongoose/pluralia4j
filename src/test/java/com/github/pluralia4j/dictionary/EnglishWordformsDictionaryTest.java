package com.github.pluralia4j.dictionary;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EnglishWordformsDictionaryTest {
    @Test
    public void testTranslateByRule(){
        final EnglishWordformsDictionary englishWordformsDictionary = new EnglishWordformsDictionary();
        assertEquals("dogs", englishWordformsDictionary.translateByRule("dog", 1));
        assertEquals("boxes", englishWordformsDictionary.translateByRule("box", 1));
        assertEquals("matches", englishWordformsDictionary.translateByRule("match", 1));
        assertEquals("bosses", englishWordformsDictionary.translateByRule("boss", 1));
        assertEquals("knives", englishWordformsDictionary.translateByRule("knife", 1));
        assertEquals("kitties", englishWordformsDictionary.translateByRule("kitty", 1));
        assertEquals("potatoes", englishWordformsDictionary.translateByRule("potato", 1));
    }
}