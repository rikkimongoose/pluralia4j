package com.github.pluralia4j.lang;

import com.github.pluralia4j.math.SeparatedDouble;
import com.github.pluralia4j.math.range.NumberRange;

import static com.github.pluralia4j.math.MathUtils.isInteger;
import static com.github.pluralia4j.math.MathUtils.separateDouble;

/**
 * Basic pluralisation rules for Serbian language
 */
public class PluralisationSerbian extends Pluralisation {
    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forIntegerAbs(int value) {
        final int valueDiv10  = value % 10,
                valueDiv100 = value % 100;

        if(valueDiv10 == 1 && valueDiv100 != 11) {
            return PluralType.ONE;
        }
        if(NumberRange.of(2, 4).contains(valueDiv10) && !NumberRange.of(12, 14).contains(valueDiv100)) {
            return PluralType.FEW;
        }
        return PluralType.OTHER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forDoubleAbs(double value) {
        if(isInteger(value)) {
            return forIntegerAbs((int)value);
        }
        final SeparatedDouble separateValue = separateDouble(value);
        final int valueF = separateValue.getFractional();
        final int valueFDiv10  = valueF % 10,
                valueFDiv100 = valueF % 100;
        if(valueFDiv10 == 1 && valueFDiv100 != 11) {
            return PluralType.ONE;
        }
        if(NumberRange.of(2, 4).contains(valueFDiv10) && !NumberRange.of(12, 14).contains(valueFDiv100)) {
            return PluralType.FEW;
        }
        return PluralType.OTHER;
    }
}
