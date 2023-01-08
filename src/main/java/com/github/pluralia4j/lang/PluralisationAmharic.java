package com.github.pluralia4j.lang;

import com.github.pluralia4j.lang.templates.PluralisationOneZeroOther;
import com.github.pluralia4j.math.range.NumberRange;
import com.github.pluralia4j.math.range.NumberRangeDouble;
import com.google.common.collect.ImmutableList;

import java.util.List;

import static com.github.pluralia4j.math.MathUtils.separateDouble;

/**
 * Basic pluralisation rules for Amharic language
 */
public final class PluralisationAmharic extends PluralisationOneZeroOther {
    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forDoubleAbs(double value) {
        final List<NumberRange<Double>> oneValues = ImmutableList.of(NumberRangeDouble.of(0.00, 0.04), NumberRange.of(0.0, 0.1));
        return (oneValues.stream().anyMatch(v -> v.contains(value)) && separateDouble(value * 10).getFractional() == 0) ?
            PluralType.ONE : PluralType.OTHER;
    }
}
