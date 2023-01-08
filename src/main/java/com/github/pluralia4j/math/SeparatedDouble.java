package com.github.pluralia4j.math;

import lombok.Value;

/**
 * Double value separated to integer and fractional parts
 */
@Value
public class SeparatedDouble {
    /**
     * Integer part (for 12.34 is 12)
     */
    int integer;

    /**
     * Fractional part (for 12.34 is 34)
     */
    int fractional;

    /**
     * Scale in source
     */
    int scale;
}
