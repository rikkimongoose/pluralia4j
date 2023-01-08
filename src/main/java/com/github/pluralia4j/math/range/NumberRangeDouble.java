package com.github.pluralia4j.math.range;

import com.github.pluralia4j.math.MathUtils;

public class NumberRangeDouble extends NumberRange<Double> {
    public NumberRangeDouble(Double valueFrom, Double valueTo) {
        super(valueFrom, valueTo);
    }

    @Override
    public boolean contains(Double value) {
        double valueScaled = MathUtils.scaleTo(value, this.getValueFrom());
        return getValueFrom().compareTo(valueScaled) <= 0 && getValueTo().compareTo(valueScaled) >= 0;
    }

    @Override
    public boolean containsStrict(Double value) {
        double valueScaled = MathUtils.scaleTo(value, this.getValueFrom());
        return getValueFrom().compareTo(valueScaled) < 0 && getValueTo().compareTo(valueScaled) > 0;
    }
}
