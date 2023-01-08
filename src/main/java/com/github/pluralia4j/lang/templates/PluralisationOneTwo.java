package com.github.pluralia4j.lang.templates;

import com.github.pluralia4j.lang.PluralType;

public abstract class PluralisationOneTwo extends PluralisationDoubleAsInteger {
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
        return PluralType.OTHER;
    }
}
