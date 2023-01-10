package com.github.pluralia4j.lang;

import com.github.pluralia4j.math.SeparatedDouble;
import com.google.common.collect.ImmutableList;

import static com.github.pluralia4j.math.MathUtils.isInteger;
import static com.github.pluralia4j.math.MathUtils.separateDouble;

/**
 * Pluralisation rules for Russian language
 */
public class PluralisationRussian extends Pluralisation {
    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forIntegerAbs(int value) {
        final int valueDiv10 = value % 10,
                  valueDiv100 = value % 100;
        if (valueDiv10 == 1 && valueDiv100 != 11) {
            return PluralType.ONE;
        }
        if (ImmutableList.of(2, 3, 4).contains(valueDiv10) && !ImmutableList.of(12, 13, 14).contains(valueDiv100)) {
            return PluralType.FEW;
        }
        return PluralType.OTHER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forDoubleAbs(double value) {
        if (isInteger(value)) {
            return forIntegerAbs((int) value);
        }
        final SeparatedDouble separateValue = separateDouble(value);
        final int valueF = separateValue.getFractional();
        return forIntegerAbs(valueF);
    }
}
