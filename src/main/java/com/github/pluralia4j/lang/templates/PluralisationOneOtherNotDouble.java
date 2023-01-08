package com.github.pluralia4j.lang.templates;

import com.github.pluralia4j.lang.PluralType;

public abstract class PluralisationOneOtherNotDouble extends PluralisationOneOther {
    /**
     * {@inheritDoc}
     */
    @Override
    public PluralType forDoubleAbs(double value) {
        return PluralType.OTHER;
    }
}
