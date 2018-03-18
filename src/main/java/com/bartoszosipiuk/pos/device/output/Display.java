package com.bartoszosipiuk.pos.device.output;

/**
 * This interface can be used with class, that displays messages on the display.
 *
 * @author Bartosz Osipiuk
 */

public interface Display {
    /**
     * Display the message on the display.
     * @param message The message to display
     */
    void display(String message);
}
