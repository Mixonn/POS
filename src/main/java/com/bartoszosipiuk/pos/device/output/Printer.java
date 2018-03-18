package com.bartoszosipiuk.pos.device.output;

/**
 * This interface can be used with class, that prints messages.
 *
 * @author Bartosz Osipiuk
 */

public interface Printer {
    /**
     * Print the message
     * @param message Message to print
     */
    void print(String message);
}
