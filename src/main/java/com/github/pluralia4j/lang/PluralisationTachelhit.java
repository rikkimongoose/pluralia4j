package com.github.pluralia4j.lang;

import com.github.pluralia4j.math.range.NumberRange;
import com.github.pluralia4j.math.range.NumberRangeDouble;
import com.google.common.collect.ImmutableList;

import java.util.List;

import static com.github.pluralia4j.math.MathUtils.isInteger;
import static com.github.pluralia4j.math.MathUtils.separateDouble;

/**
 * Basic pluralisation rules for Tachelhit language
 */
public final class PluralisationTachelhit extends Pluralisation {
    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forIntegerAbs(int value) {
        if(value == 0 || value == 1) {
            return PluralType.ONE;
        }
        if(NumberRange.of(2, 10).contains(value)) {
            return PluralType.FEW;
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
        final List<NumberRange<Double>> oneValues = ImmutableList.of(NumberRangeDouble.of(0.00, 0.04), NumberRange.of(0.0, 0.1));
        return (oneValues.stream().anyMatch(v -> v.contains(value)) && separateDouble(value * 10).getFractional() == 0) ?
                PluralType.ONE : PluralType.OTHER;
    }
}
