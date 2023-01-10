package com.github.pluralia4j.lang;

import com.github.pluralia4j.math.SeparatedDouble;
import com.github.pluralia4j.math.range.NumberRange;

import static com.github.pluralia4j.math.MathUtils.separateDouble;

/**
 * Basic pluralisation rules for Prussian language
 */
public final class PluralisationPrussian extends Pluralisation {
    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forIntegerAbs(int value) {
        if (value % 10 == 0 || NumberRange.of(11, 19).contains(value)) {
            return PluralType.ZERO;
        }
        if (value % 10 == 1 || value % 100 != 11) {
            return PluralType.ONE;
        }
        return PluralType.OTHER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forDoubleAbs(double value) {
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
