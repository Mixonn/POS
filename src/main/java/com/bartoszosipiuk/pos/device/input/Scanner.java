package com.bartoszosipiuk.pos.device.input;

/**
 * This interface can be used with class, that reads the barcodes directly.
 *
 * @author Bartosz Osipiuk
 */

public interface Scanner {
    /**
     * This method should be called when the scanner read the barcode from the product.
     * The {@link BarcodeScanListener#onBarcodeScan(String)}  BarcodeScanListener.onBarcodeScan()} should be called
     * inside to notify the listeners.
     *
     * Use it like Subject in Observer pattern.
     *
     */
    void scanBarcode();
}
