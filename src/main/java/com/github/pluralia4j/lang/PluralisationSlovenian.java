package com.github.pluralia4j.lang;

import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * Basic pluralisation rules for Slovenian language
 */
public class PluralisationSlovenian extends Pluralisation {
    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forIntegerAbs(int value) {
        final int valueDiv = value % 100;
        if(valueDiv == 1) {
            return PluralType.ONE;
        }
        if(valueDiv == 2) {
            return PluralType.TWO;
        }
        final List<Integer> valuesFew = ImmutableList.of(3, 4);
        if(valuesFew.contains(valueDiv)) {
            return PluralType.FEW;
        }
        return PluralType.OTHER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forDoubleAbs(double value) { return PluralType.FEW; }
}
