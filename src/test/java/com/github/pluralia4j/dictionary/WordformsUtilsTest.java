package com.github.pluralia4j.dictionary;

import com.google.common.collect.ImmutableList;
import junit.framework.TestCase;

import java.util.Collections;

import static com.github.pluralia4j.dictionary.WordformsUtils.*;

public class WordformsUtilsTest extends TestCase {

    public void testWordCaseByWord() {
        assertEquals(WordCase.LOWER, wordCaseByWord(""));
        assertEquals(WordCase.LOWER, wordCaseByWord("cat"));
        assertEquals(WordCase.UPPER_FIRST, wordCaseByWord("Cat"));
        assertEquals(WordCase.UPPER, wordCaseByWord("CAT"));
        assertEquals(WordCase.UPPER_FIRST, wordCaseByWord("cAt"));
    }

    public void testWordToWordCase() {
        assertEquals("cat", wordToWordCase("cat", WordCase.LOWER));
        assertEquals("Cat", wordToWordCase("cat", WordCase.UPPER_FIRST));
        assertEquals("CAT", wordToWordCase("cat", WordCase.UPPER));
    }

    public void testGetOrLast() {
        assertEquals("a", getOrLast(ImmutableList.of("a", "b"), 0));
        assertEquals("b", getOrLast(ImmutableList.of("a", "b"), 1));
        assertEquals("b", getOrLast(ImmutableList.of("a", "b"), 2));
        assertNull(getOrLast(Collections.emptyList(), 0));
    }
}