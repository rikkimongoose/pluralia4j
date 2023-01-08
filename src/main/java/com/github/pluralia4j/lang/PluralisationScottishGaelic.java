package com.github.pluralia4j.lang;

import com.github.pluralia4j.math.range.NumberRange;
import com.google.common.collect.ImmutableList;

import java.util.List;

import static com.github.pluralia4j.math.MathUtils.isInteger;

/**
 * Basic pluralisation rules for Scottish Gaelic language
 */
public final class PluralisationScottishGaelic extends Pluralisation {
    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forIntegerAbs(int value) {
        if(value == 1 || value == 11) {
            return PluralType.ONE;
        }
        if(value == 2 || value == 12) {
            return PluralType.TWO;
        }
        if(NumberRange.of(3, 10).contains(value) || NumberRange.of(13, 19).contains(value)) {
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
        return PluralType.OTHER;
    }
}
