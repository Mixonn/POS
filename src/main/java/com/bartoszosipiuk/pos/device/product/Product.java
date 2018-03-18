package com.bartoszosipiuk.pos.device.product;

import com.bartoszosipiuk.pos.device.utils.Math;

import java.math.RoundingMode;

/**
 * The Product class contains name, price and barcode. Rounds (Floor) the price to 2 decimal places.
 *
 *
 * @author Bartosz Osipiuk
 */

public class Product {
    private String name;
    private Double price;
    private String barcode;

    /**
     * The Product Class constructor
     * @param name Product name
     * @param price Product price
     * @param barcode Product barcode
     */
    public Product(String name, Double price, String barcode) {
        this.name = name;
        this.price = price;
        this.barcode = barcode;
    }

    /**
     * Returns product name
     * @return Returns product name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns product price rounded (Floor) to 2 decimal places.
     * @return Returns product price rounded (Floor) to 2 decimal places.
     */
    public Double getPrice() {
        return Math.round(price, 2, RoundingMode.FLOOR);
    }

    /**
     * Returns product barcode
     * @return Returns product barcode
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * Returns same and price separated by tabulator
     * @return Returns name and price separated by tabulator
     */
    @Override
    public String toString() {
        return name + '\t' + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!name.equals(product.name)) return false;
        if (!price.equals(product.price)) return false;
        return barcode.equals(product.barcode);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + barcode.hashCode();
        return result;
    }
}
