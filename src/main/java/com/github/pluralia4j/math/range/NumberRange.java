package com.github.pluralia4j.math.range;

public class NumberRange<T extends Comparable<T>> {
    private final T valueFrom;
    private final T valueTo;

    public NumberRange(T valueFrom, T valueTo) {
        if(valueFrom.compareTo(valueTo) < 0) {
            this.valueFrom = valueFrom;
            this.valueTo = valueTo;
        } else {
            this.valueFrom = valueTo;
            this.valueTo = valueFrom;
        }
    }

    public boolean contains(T value) {
        return valueFrom.compareTo(value) <= 0 && valueTo.compareTo(value) >= 0;
    }

    public boolean containsStrict(T value) {
        return valueFrom.compareTo(value) < 0 && valueTo.compareTo(value) > 0;
    }

    public static <T extends Comparable<T>> NumberRange<T> of(T valueFrom, T valueTo) {
        return new NumberRange<>(valueFrom, valueTo);
    }

    public T getValueFrom() { return valueFrom; }

    public T getValueTo() { return valueTo; }
}
