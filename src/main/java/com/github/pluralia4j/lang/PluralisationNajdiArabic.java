package com.github.pluralia4j.lang;

import com.github.pluralia4j.math.range.NumberRange;

import static com.github.pluralia4j.math.MathUtils.isInteger;

/**
 * Basic pluralisation rules for Najdi Arabic language
 */
public final class PluralisationNajdiArabic extends Pluralisation {
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
        if(value == 2) {
            return PluralType.TWO;
        }
        final int valueDiv = value % 100;
        if(NumberRange.of(3, 10).contains(valueDiv)) {
            return PluralType.FEW;
        }
        if(NumberRange.of(11, 99).contains(valueDiv)) {
            return PluralType.MANY;
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
        return PluralType.OTHER;
    }
}
