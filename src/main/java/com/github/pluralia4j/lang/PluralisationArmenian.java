package com.github.pluralia4j.lang;

import com.github.pluralia4j.lang.templates.PluralisationOneZeroOther;
import com.github.pluralia4j.math.range.NumberRange;
/**
 * Basic pluralisation rules for Armenian language
 */
public final class PluralisationArmenian extends PluralisationOneZeroOther {
    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forDoubleAbs(double value) {
        return (NumberRange.of(0.0, 1.5).contains(value)) ? PluralType.ONE : PluralType.OTHER;
    }
}
