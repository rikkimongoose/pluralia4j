package com.github.pluralia4j.lang;

import com.github.pluralia4j.lang.templates.PluralisationDoubleAsInteger;
import com.github.pluralia4j.math.range.NumberRange;

/**
 * Basic pluralisation rules for Belorussian language
 */
public final class PluralisationBelorussian extends PluralisationDoubleAsInteger {
    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forIntegerAbs(int value) {
        final int valueDiv10  = value % 10,
                  valueDiv100 = value % 100;

        if(valueDiv10 == 1 && valueDiv100 != 11) {
            return PluralType.ONE;
        }
        if(NumberRange.of(2, 4).contains(valueDiv10) && !NumberRange.of(12, 14).contains(valueDiv100)) {
            return PluralType.FEW;
        }
        if(NumberRange.of(5, 9).contains(valueDiv10) && !NumberRange.of(11, 14).contains(valueDiv100)) {
            return PluralType.MANY;
        }
        return PluralType.OTHER;
    }
}
