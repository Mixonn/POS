package com.bartoszosipiuk.pos.device.input;

/**
 * This interface can be used with class, that listen the barcode scanner.
 * Mostly used as Observer in Observer pattern.
 *
 * @author Bartosz Osipiuk
 * @see Scanner
 */

public interface BarcodeScanListener{
    /**
     * This method is called by {@link Scanner Scanner} implementations.
     * Update state using received barcode from {@link Scanner Scanner}.
     * @param barcode Barcode that {@link Scanner Scanner} scan.
     */
    void onBarcodeScan(String barcode);
}
