package com.github.pluralia4j.lang.templates;

import com.github.pluralia4j.lang.PluralType;
import com.github.pluralia4j.lang.Pluralisation;

import static com.github.pluralia4j.math.MathUtils.isInteger;

public abstract class PluralisationDoubleAsInteger extends Pluralisation {
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
