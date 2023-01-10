package com.github.pluralia4j.lang;

import com.github.pluralia4j.math.range.NumberRange;
import com.github.pluralia4j.math.range.NumberRangeDouble;

/**
 * Basic pluralisation rules for Irish language
 */
public final class PluralisationIrish extends Pluralisation {
    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forIntegerAbs(int value) {
        if(value == 1) {
            return PluralType.ONE;
        }
        if(value == 2) {
            return PluralType.TWO;
        }
        if(NumberRange.of(3, 6).contains(value)) {
            return PluralType.FEW;
        }
        if(NumberRange.of(7, 10).contains(value)) {
            return PluralType.MANY;
        }
        return PluralType.OTHER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forDoubleAbs(double value) {
        if(Double.compare(value, 1.0) == 0) {
            return PluralType.ONE;
        }
        if(Double.compare(value, 2.0) == 0) {
            return PluralType.TWO;
        }
        if(NumberRangeDouble.of(3.0, 6.0).contains(value)) {
            return PluralType.FEW;
        }
        if(NumberRangeDouble.of(7.0, 10.0).contains(value)) {
            return PluralType.MANY;
        }
        return PluralType.OTHER;
    }
}
