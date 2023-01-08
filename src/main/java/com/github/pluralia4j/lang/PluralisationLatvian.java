package com.github.pluralia4j.lang;

import com.github.pluralia4j.math.SeparatedDouble;
import com.github.pluralia4j.math.range.NumberRange;

import java.math.BigDecimal;

import static com.github.pluralia4j.math.MathUtils.isInteger;
import static com.github.pluralia4j.math.MathUtils.separateDouble;

/**
 * Basic pluralisation rules for Latvian language
 */
public final class PluralisationLatvian extends Pluralisation {
    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forIntegerAbs(int value) {
        if (value % 10 == 0 && NumberRange.of(11, 19).contains(value % 100)) {
            return PluralType.ZERO;
        }
        if (value % 10 == 0 && value % 100 == 11) {
            return PluralType.ONE;
        }
        return PluralType.OTHER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forDoubleAbs(double value) {
        if(isInteger(value)) {
            return forIntegerAbs((int)value);
        }
        final SeparatedDouble separateValue = separateDouble(value);
        final int decimalScale = separateValue.getScale();
        final int valueF = separateValue.getFractional();
        if((decimalScale == 2 && valueF % 10 == 0 && valueF % 100 == 11) ||
                (decimalScale != 2 && valueF % 10 == 1)) {
            return PluralType.ONE;
        }
        return PluralType.OTHER;
    }
}
