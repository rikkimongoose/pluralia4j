package com.github.pluralia4j.lang;

import com.github.pluralia4j.math.range.NumberRange;
import com.google.common.collect.ImmutableList;

import java.util.List;

import static com.github.pluralia4j.math.MathUtils.isInteger;

/**
 * Basic pluralisation rules for Manx language
 */
public class PluralisationManx extends Pluralisation {
    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forIntegerAbs(int value) {
        if(value % 100 == 1) {
            return PluralType.ONE;
        }
        if(value % 100 == 2) {
            return PluralType.TWO;
        }
        final List<Integer> allowedValues = ImmutableList.of(0, 20, 40, 60, 80);
        if(value == 0 || allowedValues.contains(value % 100)) {
            return PluralType.FEW;
        }
        if(value % 10 == 0) {
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
        if(NumberRange.of(0.0, 1.5).contains(value)) {
            return PluralType.MANY;
        }
        return PluralType.OTHER;
    }
}
