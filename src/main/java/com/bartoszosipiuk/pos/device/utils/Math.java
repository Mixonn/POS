package com.bartoszosipiuk.pos.device.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Bartosz Osipiuk on 2018-03-17.
 *
 * @author Bartosz Osipiuk
 */

public class Math {
    public static double round(double value, int places, RoundingMode rm) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, rm);
        return bd.doubleValue();
    }
}
