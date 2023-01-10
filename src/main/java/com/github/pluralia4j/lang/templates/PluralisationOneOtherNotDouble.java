package com.github.pluralia4j.lang.templates;

import com.github.pluralia4j.lang.PluralType;
import com.github.pluralia4j.math.range.NumberRangeDouble;

import static com.github.pluralia4j.math.MathUtils.isInteger;

public abstract class PluralisationOneOtherNotDouble extends PluralisationOneOther {
    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forDoubleAbs(double value) {
        if (isInteger(value)) {
            return forIntegerAbs((int) value);
        }
        return NumberRangeDouble.of(0.0, 1.0).contains(value) ? PluralType.ONE : PluralType.OTHER;
    }
}
