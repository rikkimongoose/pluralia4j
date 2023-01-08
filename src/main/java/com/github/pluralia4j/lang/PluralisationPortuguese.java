package com.github.pluralia4j.lang;

import com.github.pluralia4j.lang.templates.PluralisationOneZeroOther;
import com.github.pluralia4j.math.range.NumberRange;

import static com.github.pluralia4j.math.MathUtils.isInteger;

/**
 * Basic pluralisation rules for Portuguese language
 */
public class PluralisationPortuguese extends PluralisationOneZeroOther {
    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forDoubleAbs(double value) {
        if(isInteger(value)) {
            return forIntegerAbs((int)value);
        }
        if(NumberRange.of(0.0, 1.5).contains(value)) {
            return PluralType.MANY;
        }
        return PluralType.OTHER;
    }
}
