package com.github.pluralia4j.lang;

import com.github.pluralia4j.math.range.NumberRange;

import static com.github.pluralia4j.math.MathUtils.isInteger;

/**
 * Basic pluralisation rules for Lithuanian language
 */
public final class PluralisationLithuanian extends Pluralisation {
    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forIntegerAbs(int value) {
        final int valueDiv10  = value % 10,
                valueDiv100 = value % 100;
        if(NumberRange.of(11, 19).contains(valueDiv100)) {
            return PluralType.OTHER;
        }
        if (valueDiv10 == 1) {
            return PluralType.ZERO;
        }
        if (NumberRange.of(2, 9).contains(valueDiv10)) {
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
        return PluralType.MANY;
    }
}
