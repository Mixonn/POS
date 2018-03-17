package com.bartoszosipiuk.pos.device.utils;

import org.junit.Test;

import java.math.RoundingMode;

import static org.junit.Assert.*;

public class MathTest {

    @Test
    public void round_ShouldReturnRoundedValue(){
        assertEquals(2.34, Math.round(2.34999999, 2, RoundingMode.FLOOR), 0);
        assertEquals(2.34, Math.round(2.331234, 2, RoundingMode.CEILING),0);
        assertEquals(0.1, Math.round(0.1, 2, RoundingMode.CEILING), 0.01);

    }
}