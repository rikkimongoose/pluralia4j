package com.github.pluralia4j.dictionary;

import com.github.pluralia4j.lang.PluralType;
import junit.framework.TestCase;

public class EnglishWordformsDictionaryTest extends TestCase {

    public void testTranslateByRule() {
        final EnglishWordformsDictionary englishWordformsDictionary = new EnglishWordformsDictionary();
        assertEquals("dogs", englishWordformsDictionary.pluralByRule("dog", PluralType.OTHER));
        assertEquals("boxes", englishWordformsDictionary.pluralByRule("box", PluralType.OTHER));
        assertEquals("matches", englishWordformsDictionary.pluralByRule("match", PluralType.OTHER));
        assertEquals("bosses", englishWordformsDictionary.pluralByRule("boss", PluralType.OTHER));
        assertEquals("knives", englishWordformsDictionary.pluralByRule("knife", PluralType.OTHER));
        assertEquals("kitties", englishWordformsDictionary.pluralByRule("kitty", PluralType.OTHER));
        assertEquals("potatoes", englishWordformsDictionary.pluralByRule("potato", PluralType.OTHER));
    }
}