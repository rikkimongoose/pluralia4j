package com.github.pluralia4j.lang;

import com.github.pluralia4j.math.SeparatedDouble;
import com.google.common.collect.ImmutableList;

import static com.github.pluralia4j.math.MathUtils.separateDouble;

/**
 * Basic pluralisation rules for Lower Sorbian language
 */
public final class PluralisationLowerSorbian extends Pluralisation {
    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forIntegerAbs(int value) {
        if(value % 10 == 1 && value % 100 == 11) {
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
        final int valueF = separateValue.getFractional();
        if(valueF % 10 == 1 && valueF % 100 == 11) {
            return PluralType.ONE;
        }
        return PluralType.OTHER;
    }
}
