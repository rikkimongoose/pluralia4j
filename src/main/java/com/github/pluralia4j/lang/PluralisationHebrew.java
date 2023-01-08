package com.github.pluralia4j.lang;

import com.github.pluralia4j.math.range.NumberRange;

/**
 * Basic pluralisation rules for Hebrew language
 */
public class PluralisationHebrew extends Pluralisation {
    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forIntegerAbs(int value) {
        if (value == 1) {
            return PluralType.ONE;
        }
        if (value == 2) {
            return PluralType.TWO;
        }
        if (value % 10 == 0 && !NumberRange.of(0, 1).contains(value)) {
            return PluralType.MANY;
        }
        return PluralType.OTHER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forDoubleAbs(double value) { return PluralType.OTHER; }
}
