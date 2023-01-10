package com.github.pluralia4j.lang;

import com.google.common.collect.ImmutableList;

/**
 * Basic pluralisation rules for Czech language
 */
public final class PluralisationCzech extends Pluralisation {
    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forIntegerAbs(int value) {
        if(value == 1) {
            return PluralType.ONE;
        }
        if(ImmutableList.of(2, 3, 4).contains(value)) {
            return PluralType.FEW;
        }
        return PluralType.OTHER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forDoubleAbs(double value) { return PluralType.MANY; }
}
