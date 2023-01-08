package com.github.pluralia4j.lang;

import com.github.pluralia4j.math.SeparatedDouble;
import com.google.common.collect.ImmutableList;

import java.util.List;

import static com.github.pluralia4j.math.MathUtils.isInteger;
import static com.github.pluralia4j.math.MathUtils.separateDouble;

/**
 * Basic pluralisation rules for Filipino language
 */
public final class PluralisationFilipino extends Pluralisation {
    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forIntegerAbs(int value) {
        final List<Integer> valueOne = ImmutableList.of(1, 2, 3),
                            exceptOne = ImmutableList.of(4, 6, 9);
        if(valueOne.contains(value) || !exceptOne.contains(value % 10)) {
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
        final int valueF = separateValue.getFractional();
        if(!ImmutableList.of(4, 6, 9).contains(valueF % 10)) {
            return PluralType.ONE;
        }
        return PluralType.OTHER;
    }
}
