package com.github.pluralia4j.lang;

import junit.framework.TestCase;

public class PluralisationEnglishTest extends TestCase {
    private final static Pluralisation pluralisation = new PluralisationEnglish();

    public void testForInteger() {
        assertEquals(pluralisation.forInteger(1), PluralType.ONE);
        assertEquals(pluralisation.forInteger(2), PluralType.OTHER);
        assertEquals(pluralisation.forInteger(5), PluralType.OTHER);
        assertEquals(pluralisation.forInteger(0), PluralType.OTHER);
        assertEquals(pluralisation.forInteger(-1), PluralType.ONE);
        assertEquals(pluralisation.forInteger(-5), PluralType.OTHER);
    }

    public void testForDouble() {
        assertEquals(pluralisation.forDouble(1.0), PluralType.ONE);
        assertEquals(pluralisation.forDouble(0.5), PluralType.ONE);
        assertEquals(pluralisation.forDouble(-0.5), PluralType.ONE);
        assertEquals(pluralisation.forDouble(0.0), PluralType.OTHER);
        assertEquals(pluralisation.forDouble(1.1), PluralType.OTHER);
        assertEquals(pluralisation.forDouble(-1.1), PluralType.OTHER);
    }

    public void testWordformIndex() {
        assertEquals(pluralisation.wordformIndex(1), PluralType.ONE);
        assertEquals(pluralisation.wordformIndex(2), PluralType.OTHER);
        assertEquals(pluralisation.wordformIndex(5), PluralType.OTHER);
        assertEquals(pluralisation.wordformIndex(0), PluralType.OTHER);
        assertEquals(pluralisation.wordformIndex(-1), PluralType.ONE);
        assertEquals(pluralisation.wordformIndex(-5), PluralType.OTHER);

        assertEquals(pluralisation.wordformIndex(1.0), PluralType.ONE);
        assertEquals(pluralisation.wordformIndex(0.5), PluralType.ONE);
        assertEquals(pluralisation.wordformIndex(-0.5), PluralType.ONE);
        assertEquals(pluralisation.wordformIndex(0.0), PluralType.OTHER);
        assertEquals(pluralisation.wordformIndex(1.1), PluralType.OTHER);
        assertEquals(pluralisation.wordformIndex(-1.1), PluralType.OTHER);
    }
}
