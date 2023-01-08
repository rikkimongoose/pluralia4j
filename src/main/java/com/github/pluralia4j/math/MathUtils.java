package com.github.pluralia4j.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Math utils for number values.
 */
public final class MathUtils {

    /**
     * Separate double to integer and fraction parts.
     *
     * For 0.0 = 0 and 0
     * For 12.0 = 12 and 0
     * For 12.34 = 12 and 34
     * For 0.34 = 0 and 34
     *
     * @param value value to separate
     * @return SeparatedDouble with double data.
     */
    public static SeparatedDouble separateDouble(double value) {
        final int integerPart = (int)value;
        final BigDecimal bigDecimal = BigDecimal.valueOf(value);
        final int scale = bigDecimal.scale();
        if(scale == 0) {
            return new SeparatedDouble(integerPart, 0);
        }
        final BigDecimal valueJustFractional = bigDecimal.subtract(new BigDecimal(integerPart));
        final BigDecimal scaleFactor = new BigDecimal(10).pow(scale);
        final int fractionalPart = valueJustFractional.multiply(scaleFactor).intValue();
        return new SeparatedDouble(integerPart, fractionalPart);
    }

    public static boolean isInteger(double value) {
        return value % 1 == 0;
    }

    public static double scaleTo(double value, double scaleSource) {
        final BigDecimal bigDecimalSource = BigDecimal.valueOf(scaleSource);
        final BigDecimal bigDecimalValue = BigDecimal.valueOf(value);
        return bigDecimalValue.setScale(bigDecimalSource.scale(), RoundingMode.CEILING).doubleValue();
    }
}
