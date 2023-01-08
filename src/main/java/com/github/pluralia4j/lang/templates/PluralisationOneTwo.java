package com.github.pluralia4j.lang.templates;

import com.github.pluralia4j.lang.PluralType;
import com.github.pluralia4j.lang.Pluralisation;

public abstract class PluralisationOneTwo extends Pluralisation {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public PluralType forDoubleAbs(double value) {
        if(Double.compare(value, 1.0) == 0) {
            return PluralType.ONE;
        }
        if(Double.compare(value, 2.0) == 0) {
            return PluralType.TWO;
        }
        return PluralType.OTHER;
    }
}
