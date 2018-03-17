package com.bartoszosipiuk.pos.device.input;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Bartosz Osipiuk on 2018-03-16.
 *
 * @author Bartosz Osipiuk
 */

public class BarcodeScanner implements Scanner {
    private Set<BarcodeScanListener> listeners;

    public BarcodeScanner() {
        this.listeners = new HashSet<>();
    }

    public void addListener(BarcodeScanListener barcodeScanListener) throws ListenerAddedTwiceException {
        if(listeners.contains(barcodeScanListener)){
            throw new ListenerAddedTwiceException("Cannot add listener which is listened already");
        }
        listeners.add(barcodeScanListener);
    }

    @Override
    public void scanBarcode() {
        //todo: Connect device and use received code;
        String codeFromBarcode = generateBarcode();
        listeners.forEach(listener -> listener.onBarcodeScan(codeFromBarcode));
    }

    /**
     * Method that simulate "reading barcode"
     * @return Returns auto-generated barcode;
     */
    private String generateBarcode(){
        return String.valueOf(ThreadLocalRandom.current().nextInt(9999, 99999))+"ABC";
    }
}
