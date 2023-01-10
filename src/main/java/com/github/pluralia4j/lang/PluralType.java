package com.github.pluralia4j.lang;

/**
 * Type of pluralisation attached a word. Different languages has different systems depending on grammar.
 */
public enum PluralType {
    ZERO(5),
    ONE(0),
    TWO(4),
    FEW(2),
    MANY(3),
    OTHER(1);

    private final int index;

    PluralType(final int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
