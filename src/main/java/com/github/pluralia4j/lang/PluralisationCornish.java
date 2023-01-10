package com.github.pluralia4j.lang;

import com.github.pluralia4j.lang.templates.PluralisationDoubleAsInteger;
import com.github.pluralia4j.math.range.NumberRange;
import com.google.common.collect.ImmutableList;

/**
 * Basic pluralisation rules for Cornish language
 */
public class PluralisationCornish extends PluralisationDoubleAsInteger {
    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forIntegerAbs(int value) {
        if(value == 0) {
            return PluralType.ZERO;
        }
        if(value == 1) {
            return PluralType.ONE;
        }
        final int valueDiv = value % 100000,
                  valueDiv100 = value % 100;
        if((ImmutableList.of(2, 22, 42, 62, 82).contains(valueDiv100))
            || (value % 1000 == 0
                && (ImmutableList.of(40000, 60000, 80000).contains(valueDiv)
                    || NumberRange.of(1000, 20000).contains(valueDiv)))
            || value % 1000000 == 100000) {
            return PluralType.TWO;
        }
        if(ImmutableList.of(3, 23, 43, 63, 83).contains(valueDiv100)) {
            return PluralType.FEW;
        }
        if(ImmutableList.of(1, 21, 41, 61, 81).contains(valueDiv100)) {
            return PluralType.MANY;
        }
        return PluralType.OTHER;
    }
}
