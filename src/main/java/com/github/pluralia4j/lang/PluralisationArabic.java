package com.github.pluralia4j.lang;

import com.github.pluralia4j.lang.templates.PluralisationDoubleAsInteger;
import com.github.pluralia4j.math.range.NumberRange;

/**
 * Basic pluralisation rules for Arabic language
 */
public class PluralisationArabic extends PluralisationDoubleAsInteger {
    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forIntegerAbs(int value) {
        if(value == 0) {
            return PluralType.ZERO;
        }
        if (value == 1) {
            return PluralType.ONE;
        }
        if (value == 2) {
            return PluralType.TWO;
        }
        int valueDiv = value % 100;
        if (NumberRange.of(3, 10).contains(valueDiv)) {
            return PluralType.FEW;
        }
        if (NumberRange.of(11, 99).contains(valueDiv)) {
            return PluralType.MANY;
        }
        return PluralType.OTHER;
    }
}
