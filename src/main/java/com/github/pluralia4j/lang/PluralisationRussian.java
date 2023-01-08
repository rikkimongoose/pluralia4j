package com.github.pluralia4j.lang;

import com.github.pluralia4j.math.MathUtils;
import com.github.pluralia4j.math.SeparatedDouble;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.util.Map;

import static com.github.pluralia4j.dictionary.RussianWordformsDictionary.INDEX_FEW;
import static com.github.pluralia4j.dictionary.RussianWordformsDictionary.INDEX_MANY;
import static com.github.pluralia4j.dictionary.WordformsDictionary.INDEX_ONE;

/**
 * Pluralisation rules for Russian language
 */
public class PluralisationRussian extends Pluralisation {
    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forIntegerAbs(int value) {
        final int valueDiv10 = value % 10,
                  valueDiv100 = value % 100;
        if (valueDiv10 == 1 && valueDiv100 != 11) {
            return PluralType.ONE;
        }
        if (ImmutableList.of(2, 3, 4).contains(valueDiv10) && !ImmutableList.of(12, 13, 14).contains(valueDiv100)) {
            return PluralType.FEW;
        }
        if(valueDiv10 == 0 &&!ImmutableList.of(11, 12, 13, 14).contains(valueDiv100)) {
            return PluralType.MANY;
        }
        return PluralType.OTHER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PluralType forDoubleAbs(double value) {
        return PluralType.OTHER;
    }

    /**
     * Default {@link PluralType} =&gt; wordform's index mapping
     */
    private final Map<PluralType, Integer> defaultMap = Maps.immutableEnumMap(ImmutableMap.<PluralType, Integer>builder()
            .put(PluralType.ONE, INDEX_ONE)
            .put(PluralType.FEW, INDEX_FEW)
            .put(PluralType.MANY, INDEX_MANY)
            .build());

    /**
     * {@inheritDoc}
     */
    @Override
    protected Map<PluralType, Integer> getPluralTypeToWordformIndexMap() {
        return defaultMap;
    }
}
