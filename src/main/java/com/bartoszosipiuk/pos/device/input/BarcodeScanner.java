package com.bartoszosipiuk.pos.device.input;

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

    /**
     * This method is adding unique listener to BarcodeScanner set;
     * On every {@link BarcodeScanner#scanBarcode() scanBarcode()} call, the added {@link BarcodeScanListener BarcodeScanListener}
     * will be called {@link BarcodeScanListener#onBarcodeScan(String) BarcodeScanListener.onBarcdeScan()}
     * @param barcodeScanListener The {@link BarcodeScanListener BarcodeScanListener} to add
     * @throws ListenerAddedTwiceException Throws when the Set od BarcodeScanner contained the listener;
     */
    public void addListener(BarcodeScanListener barcodeScanListener) throws ListenerAddedTwiceException {
        if(listeners.contains(barcodeScanListener)){
            throw new ListenerAddedTwiceException("Cannot add listener which is listened already");
        }
        listeners.add(barcodeScanListener);
    }

    /**
     * This method should be called when the scanner read the barcode from the product.
     *
     * Calls {@link BarcodeScanListener#onBarcodeScan(String) BarcodeScanListener.onBarcdeScan()} on every {@link BarcodeScanListener BarcodeScanListener}
     * that the BarcodeScanner set contains.
     */
    @Override
    public void scanBarcode() {
        //todo: Connect device and use received code;
        String codeFromBarcode = generateBarcode();
        listeners.forEach(listener -> listener.onBarcodeScan(codeFromBarcode));
    }

    /**
     * Method that simulate "reading barcode" from barcode scanner.
     * @return Returns auto-generated barcode;
     */
    private String generateBarcode(){
        return String.valueOf(ThreadLocalRandom.current().nextInt(9999, 99999))+"ABC";
    }
}
