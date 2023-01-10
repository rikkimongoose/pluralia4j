package com.github.pluralia4j.lang;

import com.github.pluralia4j.lang.templates.PluralisationDoubleAsInteger;
import com.github.pluralia4j.math.range.NumberRange;
import com.google.common.collect.ImmutableList;
import java.util.List;

/**
 * Basic pluralisation rules for Breton language
 */
public final class PluralisationBreton extends PluralisationDoubleAsInteger {
    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forIntegerAbs(int value) {
        final int valueDiv10  = value % 10,
                valueDiv100 = value % 100;
        final List<Integer> exceptOne = ImmutableList.of(11, 71, 91);
        if(valueDiv10 == 1 && !exceptOne.contains(valueDiv100)) {
            return PluralType.ONE;
        }
        final List<Integer> exceptTwo = ImmutableList.of(12, 72, 92);
        if(valueDiv10 == 2 && !exceptTwo.contains(valueDiv100)) {
            return PluralType.TWO;
        }
        final List<Integer> valueFew = ImmutableList.of(3, 4, 9);
        List<NumberRange<Integer>> exceptFew = ImmutableList.of(
                NumberRange.of(10, 19),
                NumberRange.of(70, 79),
                NumberRange.of(90, 99)
        );
        if(valueFew.contains(valueDiv10) && exceptFew.stream().noneMatch(r -> r.contains(valueDiv100))) {
            return PluralType.FEW;
        }
        if(value != 0 && value % 1000000 == 0) {
            return PluralType.MANY;
        }
        return PluralType.OTHER;
    }
}
