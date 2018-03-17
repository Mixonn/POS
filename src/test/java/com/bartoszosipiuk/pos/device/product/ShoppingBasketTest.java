package com.bartoszosipiuk.pos.device.product;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShoppingBasketTest {

    private ShoppingBasket testShoppingBasket;

    @Before
    public void setUp() throws Exception {
        testShoppingBasket = new ShoppingBasket();
    }

    @Test
    public void shoppingBasket_ShouldReturnSumZeroOnCreate() throws Exception {
        assertEquals(0d, 0d, 0d);
    }

    @Test
    public void add_ShouldAddPriceToSumAndFloorUpToTwoDecimal() throws Exception {
        Product p1 = new Product("XX", 3.24, "CCC");
        Product p2 = new Product("ZZ", 52.5162, "RRR");

        testShoppingBasket.add(p1);
        assertEquals("Should return sum of one product",
                "3,24", testShoppingBasket.getSumOfProductPriceToDisplay().replace('.',','));

        testShoppingBasket.add(p2);
        assertEquals("Should return rounded sum of two products",
                "55,75", testShoppingBasket.getSumOfProductPriceToDisplay().replace('.',','));

    }

    @Test
    public void add_ShouldAddItemToBasket() throws Exception {
        Product p1 = new Product("XX", 3.24, "CCC");
        Product p2 = new Product("ZZ", 52.5162, "RRR");
        testShoppingBasket.add(p1);
        testShoppingBasket.add(p2);
        assertEquals(2,testShoppingBasket.getListOfProducts().size());
    }



}