package com.github.pluralia4j.lang;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.util.Map;

import static com.github.pluralia4j.dictionary.EnglishWordformsDictionary.INDEX_MANY;
import static com.github.pluralia4j.dictionary.WordformsDictionary.INDEX_ONE;

/**
 * Basic pluralisation rules for Russian language
 */
public abstract class Pluralisation {
    /**
     * {@link PluralType} by integer value
     * @param value that makes word plural
     * @return PluralType for value
     */
    public PluralType forInteger(int value) {
        return forIntegerAbs(Math.abs(value));
    }

    /**
     * {@link PluralType} by absolute integer value
     * @param value that makes word plural
     * @return {@link PluralType} for value
     */
    protected PluralType forIntegerAbs(int value) {
        return (value == 1) ? PluralType.ONE : PluralType.MANY;
    }

    /**
     * {@link PluralType} by double value (i.e. "0.25 inch; 1.25 inches")
     * @param value that makes word plural
     * @return {@link PluralType} for value
     */
    public PluralType forDouble(double value) {
        return forDoubleAbs(Math.abs(value));
    }

    /**
     * {@link PluralType} by absolute double value (i.e. "0.25 inch; 1.25 inches")
     * @param value that makes word plural
     * @return {@link PluralType} for value
     */
    protected PluralType forDoubleAbs(double value) {
        int valueAsInt = (int)value;
        return ((double)valueAsInt == value) ? forIntegerAbs(valueAsInt) : ((value > 0.0 && value < 1.0) ? PluralType.ONE : PluralType.MANY);
    }

    /**
     * Default {@link PluralType} =&gt; wordform's index mapping
     */
    private final Map<PluralType, Integer> defaultMap = Maps.immutableEnumMap(ImmutableMap.<PluralType, Integer>builder()
            .put(PluralType.ONE, INDEX_ONE)
            .put(PluralType.MANY, INDEX_MANY)
            .build());

    /**
     * Get {@link PluralType} =&gt; wordform's index Map
     *
     * @return <code>EnumMap&lt;PluralType, Integer&gt;</code> with mappings
     */
    protected Map<PluralType, Integer> getPluralTypeToWordformIndexMap() {
        return defaultMap;
    }

    /**
     * Get wordform's index for a {@link PluralType}
     * @param pluralType {@link PluralType} which index is loaded
     * @return the proper wordform's index for {@link PluralType}, otherwise 0
     */
    protected int wordformIndexByPluralType(PluralType pluralType) {
        return getPluralTypeToWordformIndexMap().getOrDefault(pluralType, 0);
    }

    /**
     * Get wordform's index by number
     *
     * @param number number of items that makes them plural
     * @return index of wordform in dictionary record for current language by number
     */
    public int wordformIndex(Number number) {
        return wordformIndexByPluralType(forDouble(number.doubleValue()));
    }
}
