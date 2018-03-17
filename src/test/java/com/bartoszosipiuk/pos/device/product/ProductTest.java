package com.bartoszosipiuk.pos.device.product;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest {
    private Product testProduct;

    @Before
    public void setUp() throws Exception{
        testProduct = new Product("XYZ", 45.1699, "E4252");
    }

    @Test
    public void getName_ShouldReturnName() throws Exception {
        assertEquals("XYZ", testProduct.getName());
    }

    @Test
    public void getPrice_ShouldReturnPrice() throws Exception {
        assertEquals(45.1699d, testProduct.getPrice(), 0.01);
    }

    @Test
    public void getBarcode_ShouldReturnBarcode() throws Exception {
        assertEquals("E4252", testProduct.getBarcode());
    }

}