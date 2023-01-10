package com.github.pluralia4j.math.range;

import com.github.pluralia4j.math.MathUtils;

/**
 * Class for range of two comparable doubles
 */
public class NumberRangeDouble extends NumberRange<Double> {
    /**
     * Constructor
     *
     * @param valueFrom value from
     * @param valueTo   value to
     */
    public NumberRangeDouble(Double valueFrom, Double valueTo) {
        super(valueFrom, valueTo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(Double value) {
        final double valueScaled = MathUtils.scaleTo(value, this.getValueFrom());
        return getValueFrom().compareTo(valueScaled) <= 0 && getValueTo().compareTo(valueScaled) >= 0;
    }
}
