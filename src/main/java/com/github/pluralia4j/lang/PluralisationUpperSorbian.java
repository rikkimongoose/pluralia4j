package com.github.pluralia4j.lang;

import com.github.pluralia4j.math.SeparatedDouble;
import com.github.pluralia4j.math.range.NumberRange;
import com.google.common.collect.ImmutableList;

import static com.github.pluralia4j.math.MathUtils.isInteger;
import static com.github.pluralia4j.math.MathUtils.separateDouble;

public class PluralisationUpperSorbian extends Pluralisation {
    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forIntegerAbs(int value) {
        final int valueDiv = value % 100;
        if(valueDiv == 1) {
            return PluralType.ONE;
        }
        if(valueDiv == 2) {
            return PluralType.TWO;
        }
        if(ImmutableList.of(3, 4).contains(valueDiv)) {
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
        final SeparatedDouble separateValue = separateDouble(value);
        final int valueFDiv100 = separateValue.getFractional() % 100;
        if(valueFDiv100 == 1) {
            return PluralType.ONE;
        }
        if(valueFDiv100 == 2) {
            return PluralType.TWO;
        }
        if(ImmutableList.of(3, 4).contains(valueFDiv100)) {
            return PluralType.FEW;
        }
        return PluralType.OTHER;
    }
}
