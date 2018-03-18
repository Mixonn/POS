package com.bartoszosipiuk.pos.device.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Bartosz Osipiuk
 */

public class Math {
    private Math(){}

    /**
     * Returns rounded decimal.
     * @param value Value to round
     * @param places How many decimal places to round
     * @param rm Witch {@link RoundingMode} to use
     * @return Returns rounded decimal.
     */
    public static double round(double value, int places, RoundingMode rm) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, rm);
        return bd.doubleValue();
    }
}
