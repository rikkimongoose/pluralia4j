package com.github.pluralia4j.math;

import java.math.BigDecimal;

/**
 *
 */
public final class MathUtils {
    /**
     *
     * @param value
     * @return
     */
    public static SeparatedDouble separateDouble(double value) {
        final int integerPart = (int)value;
        final BigDecimal bigDecimal = BigDecimal.valueOf(value);
        int scale = bigDecimal.scale();
        if(scale == 0) {
            return new SeparatedDouble(integerPart, 0);
        }
        BigDecimal valueJustFractional = bigDecimal.subtract(new BigDecimal(integerPart));
        BigDecimal scaleFactor = new BigDecimal(10).pow(scale);
        int fractionalPart = valueJustFractional.multiply(scaleFactor).intValue();
        return new SeparatedDouble(integerPart, fractionalPart);
    }
}
