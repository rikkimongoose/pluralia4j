package com.github.pluralia4j.lang;

import com.github.pluralia4j.math.range.NumberRange;
import com.github.pluralia4j.math.range.NumberRangeDouble;

/**
 * Basic pluralisation rules for Maltese language
 */
public final class PluralisationMaltese extends Pluralisation {
    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forIntegerAbs(int value) {
        if(value == 1) {
            return PluralType.ONE;
        }
        if(value == 0 || NumberRange.of(2, 10).contains(value % 100)) {
            return PluralType.FEW;
        }
        if(NumberRange.of(11, 19).contains(value % 100)) {
            return PluralType.MANY;
        }
        return PluralType.OTHER;
    }
}
