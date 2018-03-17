package com.bartoszosipiuk.pos.device.input;

import com.bartoszosipiuk.pos.device.PointOfSale;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BarcodeScannerTest {

    private BarcodeScanner testBarcodeScanner;

    private PointOfSale testPointOfSale;

    @Before
    public void setUp() throws ListenerAddedTwiceException {
        testBarcodeScanner = new BarcodeScanner();
        testPointOfSale = mock(PointOfSale.class);
        testBarcodeScanner.addListener(testPointOfSale);
    }

    @Test
    public void scanBarcode_ShouldCallOnBarcodeScan(){
        testBarcodeScanner.scanBarcode();
        Mockito.verify(testPointOfSale).onBarcodeScan(anyString());
    }

    @Test(expected = ListenerAddedTwiceException.class)
    public void addListener_ShouldThrowExceptionWhenTryToAddThisSameListenerTwice() throws ListenerAddedTwiceException {
        testBarcodeScanner.addListener(testPointOfSale);
        testBarcodeScanner.addListener(testPointOfSale);
    }
}