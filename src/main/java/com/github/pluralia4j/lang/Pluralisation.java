package com.github.pluralia4j.lang;

import static com.github.pluralia4j.math.MathUtils.isInteger;

/**
 * Basic pluralisation rules for a language
 */
public abstract class Pluralisation {
    /**
     * {@link PluralType} by integer value
     * @param value that makes word plural
     * @return PluralType for value
     */
    public PluralType forInteger(int value) {
        return forIntegerAbs(Math.abs(value));
    }

    /**
     * {@link PluralType} by absolute integer value
     * @param value that makes word plural
     * @return {@link PluralType} for value
     */
    protected PluralType forIntegerAbs(int value) {
        return (value == 1) ? PluralType.ONE : PluralType.OTHER;
    }

    /**
     * {@link PluralType} by double value (i.e. "0.25 inch; 1.25 inches")
     * @param value that makes word plural
     * @return {@link PluralType} for value
     */
    public PluralType forDouble(double value) {
        return forDoubleAbs(Math.abs(value));
    }

    /**
     * {@link PluralType} by absolute double value (i.e. "0.25 inch; 1.25 inches")
     * @param value that makes word plural
     * @return {@link PluralType} for value
     */
    protected PluralType forDoubleAbs(double value) {
        return (isInteger(value)) ? forIntegerAbs((int) value) : PluralType.OTHER;
    }

    /**
     * Get wordform's index by number
     *
     * @param number number of items that makes them plural
     * @return PluralType for current language by number
     */
    public PluralType wordformIndex(Number number) {
        return (number instanceof Integer) ?
                forInteger(number.intValue()) :
                forDouble(number.doubleValue());
    }
}
