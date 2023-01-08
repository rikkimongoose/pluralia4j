package com.github.pluralia4j.lang;

import static com.github.pluralia4j.math.MathUtils.isInteger;

/**
 * Basic pluralisation rules for Welsh language
 */
public final class PluralisationWelsh extends Pluralisation {
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
        if(value == 2) {
            return PluralType.TWO;
        }
        if(value == 3) {
            return PluralType.FEW;
        }
        if(value == 6) {
            return PluralType.MANY;
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
