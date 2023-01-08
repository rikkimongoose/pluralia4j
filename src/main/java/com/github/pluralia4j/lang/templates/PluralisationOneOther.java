package com.github.pluralia4j.lang.templates;

import com.github.pluralia4j.lang.PluralType;
import com.github.pluralia4j.lang.Pluralisation;
import com.google.common.collect.ImmutableList;

import java.util.List;

public abstract class PluralisationOneOther extends Pluralisation {
    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forIntegerAbs(int value) {
        final List<Integer> oneValues = ImmutableList.of(1);
        return (oneValues.contains(value)) ? PluralType.ONE : PluralType.OTHER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PluralType forDoubleAbs(double value) {
        final List<Double> oneValues = ImmutableList.of(1.0);
        return (oneValues.stream().anyMatch(v -> Double.compare(value, v) == 0)) ? PluralType.ONE : PluralType.OTHER;
    }
}
