package com.github.pluralia4j.lang;

import com.github.pluralia4j.math.MathUtils;
import com.github.pluralia4j.math.SeparatedDouble;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 *
 */
public class PluralisationRussian extends Pluralisation {
    /**
     *
     * @param value
     * @return
     */
    @Override
    public PluralType forInteger(int value) {
        if (value % 10 == 1 && value % 100 != 11) {
            return PluralType.ONE;
        }
        if (value % 10 >= 2 && value % 10 <= 4 && (value % 100 < 10 || value % 100 >= 20)) {
            return PluralType.FEW;
        }
        return PluralType.MANY;
    }

    /**
     *
     * @param value
     * @return
     */
    @Override
    public PluralType forDouble(double value) {
        final SeparatedDouble separatedDouble = MathUtils.separateDouble(value);
        return forInteger((separatedDouble.getFractional() == 0) ?
                separatedDouble.getInteger() :
                separatedDouble.getFractional());
    }

    /**
     *
     */
    private final Map<PluralType, Integer> defaultMap = Maps.immutableEnumMap(ImmutableMap.<PluralType, Integer>builder()
            .put(PluralType.ONE, 0)
            .put(PluralType.FEW, 1)
            .put(PluralType.MANY, 2)
            .build());

    /**
     *
     * @return
     */
    @Override
    protected Map<PluralType, Integer> getWordformByPlural() {
        return defaultMap;
    }
}
