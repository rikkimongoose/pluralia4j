package com.github.pluralia4j.math.range;

import lombok.Data;

/**
 * Class for range of two comparable items
 *
 * @param <T> comparable <code>T</code> type
 */
@Data
public class NumberRange<T extends Comparable<T>> {
    private final T valueFrom;
    private final T valueTo;

    /**
     * Constructor
     *
     * @param valueFrom
     * @param valueTo
     */
    public NumberRange(T valueFrom, T valueTo) {
        if (valueFrom.compareTo(valueTo) < 0) {
            this.valueFrom = valueFrom;
            this.valueTo = valueTo;
        } else {
            this.valueFrom = valueTo;
            this.valueTo = valueFrom;
        }
    }

    /**
     * Check, is valueFrom <= value <= valueTo
     *
     * @param value value to check
     * @return <code>true</code> if valueFrom <= value <= valueTo
     */
    public boolean contains(T value) {
        return valueFrom.compareTo(value) <= 0 && valueTo.compareTo(value) >= 0;
    }

    /**
     * Returns Number Range from valueFrom to valueTo
     *
     * @param valueFrom value from
     * @param valueTo   value to
     * @param <T>       comparable <code>T</code> type
     * @return NumberRange of <code>T</code> type from valueFrom to valueTo
     */
    public static <T extends Comparable<T>> NumberRange<T> of(T valueFrom, T valueTo) {
        return new NumberRange<>(valueFrom, valueTo);
    }
}
