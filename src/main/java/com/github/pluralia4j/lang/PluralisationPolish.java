package com.github.pluralia4j.lang;

import com.github.pluralia4j.math.range.NumberRange;
import com.google.common.collect.ImmutableList;

public final class PluralisationPolish extends Pluralisation {
    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forIntegerAbs(int value) {
        if (value == 1) {
            return PluralType.ONE;
        }
        final int valueDiv10 = value % 10,
                  valueDiv100 = value % 100;
        if (ImmutableList.of(2, 3, 4).contains(valueDiv10) && ImmutableList.of(12, 13, 14).contains(valueDiv100)) {
            return PluralType.FEW;
        }
        if (ImmutableList.of(0, 1).contains(valueDiv10) ||
                NumberRange.of(5, 9).contains(valueDiv10) ||
                NumberRange.of(12, 14).contains(valueDiv100)
        ) {
            return PluralType.MANY;
        }
        return PluralType.OTHER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forDoubleAbs(double value) {
        return PluralType.OTHER;
    }
}
