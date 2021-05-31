package com.github.pluralia4j.lang;

import junit.framework.TestCase;

import static com.github.pluralia4j.dictionary.EnglishWordformsDictionary.INDEX_MANY;
import static com.github.pluralia4j.dictionary.WordformsDictionary.INDEX_ONE;

public class PluralisationTest extends TestCase {
    private final static Pluralisation pluralisation = new PluralisationEnglish();

    public void testForInteger() {
        assertEquals(pluralisation.forInteger(1), PluralType.ONE);
        assertEquals(pluralisation.forInteger(2), PluralType.MANY);
        assertEquals(pluralisation.forInteger(5), PluralType.MANY);
        assertEquals(pluralisation.forInteger(0), PluralType.MANY);
        assertEquals(pluralisation.forInteger(-1), PluralType.ONE);
        assertEquals(pluralisation.forInteger(-5), PluralType.MANY);
    }

    public void testForDouble() {
        assertEquals(pluralisation.forDouble(1.0), PluralType.ONE);
        assertEquals(pluralisation.forDouble(0.5), PluralType.ONE);
        assertEquals(pluralisation.forDouble(-0.5), PluralType.ONE);
        assertEquals(pluralisation.forDouble(0.0), PluralType.MANY);
        assertEquals(pluralisation.forDouble(1.1), PluralType.MANY);
        assertEquals(pluralisation.forDouble(-1.1), PluralType.MANY);
    }

    public void testWordformIndex() {

        assertEquals(pluralisation.wordformIndex(1), INDEX_ONE);
        assertEquals(pluralisation.wordformIndex(2), INDEX_MANY);
        assertEquals(pluralisation.wordformIndex(5), INDEX_MANY);
        assertEquals(pluralisation.wordformIndex(0), INDEX_MANY);
        assertEquals(pluralisation.wordformIndex(-1), INDEX_ONE);
        assertEquals(pluralisation.wordformIndex(-5), INDEX_MANY);

        assertEquals(pluralisation.wordformIndex(1.0), INDEX_ONE);
        assertEquals(pluralisation.wordformIndex(0.5), INDEX_ONE);
        assertEquals(pluralisation.wordformIndex(-0.5), INDEX_ONE);
        assertEquals(pluralisation.wordformIndex(0.0), INDEX_MANY);
        assertEquals(pluralisation.wordformIndex(1.1), INDEX_MANY);
        assertEquals(pluralisation.wordformIndex(-1.1), INDEX_MANY);
    }
}