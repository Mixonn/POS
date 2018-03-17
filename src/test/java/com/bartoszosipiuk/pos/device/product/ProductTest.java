package com.bartoszosipiuk.pos.device.product;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest {
    private final String NAME = "Temp";
    private final String BARCODE = "E4252";
    private Product testProduct;

    @Before
    public void setUp() throws Exception{
        testProduct = new Product(NAME, 45.1699, BARCODE);
    }

    @Test
    public void getName_ShouldReturnName() throws Exception {
        assertEquals(NAME, testProduct.getName());
    }

    @Test
    public void getPrice_ShouldReturnPrice() throws Exception {
        assertEquals(45.16d, testProduct.getPrice(), 0);
    }

    @Test
    public void getBarcode_ShouldReturnBarcode() throws Exception {
        assertEquals(BARCODE, testProduct.getBarcode());
    }

}