package com.github.pluralia4j.math;

import junit.framework.TestCase;

import static com.github.pluralia4j.math.MathUtils.isInteger;
import static com.github.pluralia4j.math.MathUtils.separateDouble;

public class MathUtilsTest extends TestCase {
    public void testSeparateDouble() {
        final SeparatedDouble separatedDouble0_0 = new SeparatedDouble(0, 0, 0);
        final SeparatedDouble separatedDouble12_0 = new SeparatedDouble(12, 0, 0);
        final SeparatedDouble separatedDouble12_34 = new SeparatedDouble(12, 34, 2);
        final SeparatedDouble separatedDouble0_34 = new SeparatedDouble(0, 34, 2);

        assertEquals(separatedDouble0_0, separateDouble(0.0));
        assertEquals(separatedDouble12_0, separateDouble(12.0));
        assertEquals(separatedDouble12_34, separateDouble(12.34));
        assertEquals(separatedDouble0_34, separateDouble(0.34));
    }

    public void testIsInteger() {
        assertTrue(isInteger(0.0));
        assertTrue(isInteger(12.0));
        assertFalse(isInteger(12.34));
        assertFalse(isInteger(0.34));
    }

}