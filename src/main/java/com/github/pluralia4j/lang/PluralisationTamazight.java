package com.github.pluralia4j.lang;

import com.github.pluralia4j.lang.templates.PluralisationDoubleAsInteger;
import com.github.pluralia4j.math.range.NumberRange;
import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * Basic pluralisation rules for Tamazight language
 */
public class PluralisationTamazight extends PluralisationDoubleAsInteger {
    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forIntegerAbs(int value) {
        final List<NumberRange<Integer>> valuesOne = ImmutableList.of(
                NumberRange.of(0, 1),
                NumberRange.of(11, 99)
        );
        if(valuesOne.stream().anyMatch(r -> r.contains(value))) {
            return PluralType.ONE;
        }
        return PluralType.OTHER;
    }
}
