package com.github.pluralia4j.lang;

import com.github.pluralia4j.math.range.NumberRange;

/**
 * Basic pluralisation rules for Romanian language
 */
public class PluralisationRomanian extends Pluralisation {
    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forIntegerAbs(int value) {
        if (value == 1) {
            return PluralType.ZERO;
        }
        if (value == 0 || NumberRange.of(2, 19).contains(value % 100)) {
            return PluralType.ONE;
        }
        return PluralType.OTHER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forDoubleAbs(double value) {
        return PluralType.FEW;
    }
}
