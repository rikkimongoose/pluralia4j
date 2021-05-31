package com.github.pluralia4j.lang;

import com.github.pluralia4j.math.MathUtils;
import com.github.pluralia4j.math.SeparatedDouble;
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
        if (value % 10 == 1 && value % 100 != 11) {
            return PluralType.ONE;
        }
        if (value % 10 >= 2 && value % 10 <= 4 && (value % 100 < 10 || value % 100 >= 20)) {
            return PluralType.FEW;
        }
        return PluralType.MANY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PluralType forDouble(double value) {
        final SeparatedDouble separatedDouble = MathUtils.separateDouble(Math.abs(value));
        final int integerPart = separatedDouble.getInteger(),
                    fractionalPart = separatedDouble.getFractional();
        return (fractionalPart == 1)
                ? PluralType.MANY
                : forInteger((fractionalPart == 0)
                                ? integerPart
                                : fractionalPart);
    }

    /**
     * Default {@link PluralType} => wordform's index mapping
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
