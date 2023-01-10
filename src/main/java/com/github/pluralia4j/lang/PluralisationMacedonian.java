package com.github.pluralia4j.lang;

import com.github.pluralia4j.math.SeparatedDouble;
import com.google.common.collect.ImmutableList;

import static com.github.pluralia4j.math.MathUtils.separateDouble;

public final class PluralisationMacedonian extends Pluralisation {
    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forIntegerAbs(int value) {
        final int valueDiv100 = value % 100;
        if(valueDiv100 == 1) {
            return PluralType.ONE;
        }
        if (valueDiv100 == 2) {
            return PluralType.TWO;
        }
        if (ImmutableList.of(3, 4).contains(valueDiv100)) {
            return PluralType.FEW;
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
        final int valueFDiv100 = valueF % 100;
        if(valueFDiv100 == 1) {
            return PluralType.ONE;
        }
        if (valueFDiv100 == 2) {
            return PluralType.TWO;
        }
        if (ImmutableList.of(3, 4).contains(valueFDiv100)) {
            return PluralType.FEW;
        }
        return PluralType.MANY;
    }
}
