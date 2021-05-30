package com.github.pluralia4j.lang;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 *
 */
public abstract class Pluralisation {
    /**
     *
     * @param value
     * @return
     */
    public PluralType forInteger(int value) {
        return (value == 1 || value == 0) ? PluralType.ONE : PluralType.MANY;
    }

    /**
     *
     * @param value
     * @return
     */
    public PluralType forDouble(double value) {
        int valueAsInt = (int)value;
        //i.e. "0.25 inch; 1.25 inches."
        return ((double)valueAsInt == value) ? forInteger(valueAsInt) : ((value > 0.0 && value < 1.0) ? PluralType.ONE : PluralType.MANY);
    }

    private final Map<PluralType, Integer> defaultMap = Maps.immutableEnumMap(ImmutableMap.<PluralType, Integer>builder()
            .put(PluralType.ONE, 0)
            .put(PluralType.MANY, 1)
            .build());

    protected Map<PluralType, Integer> getWordformByPlural() {
        return defaultMap;
    }

    public int wordformIndexByPluralType(PluralType pluralType) {
        if(!getWordformByPlural().containsKey(pluralType)) {
            return 0;
        }
        return getWordformByPlural().get(pluralType);
    }

    public int wordformIndex(Number number) {
        return wordformIndexByPluralType(forDouble(number.doubleValue()));
    }
}
