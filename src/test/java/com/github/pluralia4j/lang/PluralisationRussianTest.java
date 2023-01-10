package com.github.pluralia4j.lang;

import junit.framework.TestCase;

public class PluralisationRussianTest extends TestCase {

    private final static Pluralisation pluralisation = new PluralisationRussian();

    public void testForInteger() {
        assertEquals(pluralisation.forInteger(1), PluralType.ONE);
        assertEquals(pluralisation.forInteger(2), PluralType.FEW);
        assertEquals(pluralisation.forInteger(5), PluralType.OTHER);
        assertEquals(pluralisation.forInteger(0), PluralType.OTHER);
        assertEquals(pluralisation.forInteger(-1), PluralType.ONE);
        assertEquals(pluralisation.forInteger(-5), PluralType.OTHER);
    }

    public void testForDouble() {
        assertEquals(pluralisation.forDouble(1.0), PluralType.ONE);
        assertEquals(pluralisation.forDouble(3.0), PluralType.FEW);
        assertEquals(pluralisation.forDouble(5.0), PluralType.OTHER);
        assertEquals(pluralisation.forDouble(0.3), PluralType.FEW);
        assertEquals(pluralisation.forDouble(0.13), PluralType.OTHER);
        assertEquals(pluralisation.forDouble(0.33), PluralType.FEW);
        assertEquals(pluralisation.forDouble(0.5), PluralType.OTHER);
        assertEquals(pluralisation.forDouble(-0.5), PluralType.OTHER);
        assertEquals(pluralisation.forDouble(0.0), PluralType.OTHER);
        assertEquals(pluralisation.forDouble(1.1), PluralType.ONE);
        assertEquals(pluralisation.forDouble(-1.1), PluralType.ONE);
    }

    public void testWordformIndex() {
        assertEquals(pluralisation.wordformIndex(1), PluralType.ONE);
        assertEquals(pluralisation.wordformIndex(2), PluralType.FEW);
        assertEquals(pluralisation.wordformIndex(5), PluralType.OTHER);
        assertEquals(pluralisation.wordformIndex(0), PluralType.OTHER);
        assertEquals(pluralisation.wordformIndex(-1), PluralType.ONE);
        assertEquals(pluralisation.wordformIndex(-5), PluralType.OTHER);

        assertEquals(pluralisation.wordformIndex(1.0), PluralType.ONE);
        assertEquals(pluralisation.wordformIndex(0.5), PluralType.OTHER);
        assertEquals(pluralisation.wordformIndex(-0.5), PluralType.OTHER);
        assertEquals(pluralisation.wordformIndex(0.0), PluralType.OTHER);
        assertEquals(pluralisation.wordformIndex(1.1), PluralType.ONE);
        assertEquals(pluralisation.wordformIndex(-1.1), PluralType.ONE);
    }
}