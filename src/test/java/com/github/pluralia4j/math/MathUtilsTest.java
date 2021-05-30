package com.github.pluralia4j.math;

import org.junit.Test;

import static com.github.pluralia4j.math.MathUtils.separateDouble;
import static org.junit.Assert.*;

public class MathUtilsTest {
    @Test
    public void testSeparateDouble() {
        final SeparatedDouble separatedDouble0_0 = new SeparatedDouble(0, 0);
        final SeparatedDouble separatedDouble12_0 = new SeparatedDouble(12, 0);
        final SeparatedDouble separatedDouble12_34 = new SeparatedDouble(12, 34);
        final SeparatedDouble separatedDouble0_34 = new SeparatedDouble(0, 34);

        assertEquals(separatedDouble0_0, separateDouble(0.0));
        assertEquals(separatedDouble12_0, separateDouble(12.0));
        assertEquals(separatedDouble12_34, separateDouble(12.34));
        assertEquals(separatedDouble0_34, separateDouble(0.34));
    }


}