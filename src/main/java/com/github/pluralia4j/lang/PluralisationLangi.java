package com.github.pluralia4j.lang;

import com.github.pluralia4j.math.range.NumberRangeDouble;

/**
 * Basic pluralisation rules for Langi language
 */
public final class PluralisationLangi extends Pluralisation {
    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forIntegerAbs(int value) {
        if(value == 0) {
            return PluralType.ZERO;
        }
        if(value == 1) {
            return PluralType.ONE;
        }
        return PluralType.OTHER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forDoubleAbs(double value) {
        if(Double.compare(value, 0) == 0) {
            return PluralType.ZERO;
        }
        if(NumberRangeDouble.of(0.1, 1.6).contains(value)) {
            return PluralType.ONE;
        }
        return PluralType.OTHER;
    }
}
