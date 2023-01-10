package com.github.pluralia4j.lang;

import com.github.pluralia4j.lang.templates.PluralisationDoubleAsInteger;

/**
 * Basic pluralisation rules for Welsh language
 */
public final class PluralisationWelsh extends PluralisationDoubleAsInteger {
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
}
