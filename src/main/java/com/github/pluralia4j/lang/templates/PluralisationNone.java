package com.github.pluralia4j.lang.templates;

import com.github.pluralia4j.lang.PluralType;
import com.github.pluralia4j.lang.Pluralisation;

public abstract class PluralisationNone extends Pluralisation {
    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forIntegerAbs(int value) {
        return PluralType.OTHER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forDoubleAbs(double value) {
        return PluralType.OTHER;
    }
}
