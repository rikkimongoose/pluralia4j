package com.github.pluralia4j.lang;

import com.github.pluralia4j.lang.templates.PluralisationOneZeroOther;
import com.github.pluralia4j.math.SeparatedDouble;

import static com.github.pluralia4j.math.MathUtils.separateDouble;

/**
 * Basic pluralisation rules for Sinhala language
 */
public final class PluralisationSinhala extends PluralisationOneZeroOther {
    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forDoubleAbs(double value) {
        final SeparatedDouble separateValue = separateDouble(value);
        final int valueV = separateValue.getInteger(),
                valueF = separateValue.getFractional();
        if(valueV == 0 && valueF == 1) {
            return PluralType.ONE;
        }
        return PluralType.OTHER;
    }
}
