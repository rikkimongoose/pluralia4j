package com.github.pluralia4j.lang;

import com.github.pluralia4j.math.range.NumberRange;

/**
 * Basic pluralisation rules for Slovak language
 */
public final class PluralisationSlovak extends Pluralisation {
    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forIntegerAbs(int value) {
        if(value == 1) {
            return PluralType.ONE;
        }
        final NumberRange<Integer> valuesFew = NumberRange.of(2, 4);
        if(valuesFew.contains(value)) {
            return PluralType.FEW;
        }
        return PluralType.OTHER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forDoubleAbs(double value) { return PluralType.MANY; }
}
