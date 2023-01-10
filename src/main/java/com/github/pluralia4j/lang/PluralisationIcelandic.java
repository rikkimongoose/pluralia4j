package com.github.pluralia4j.lang;

/**
 * Basic pluralisation rules for Icelandic language
 */
public final class PluralisationIcelandic extends Pluralisation {
    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forIntegerAbs(int value) {
        if (value % 10 == 1 && value % 100 != 11) {
            return PluralType.ONE;
        }
        return PluralType.OTHER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forDoubleAbs(double value) { return PluralType.OTHER; }
}
