package com.bartoszosipiuk.pos.device.product;

import com.bartoszosipiuk.pos.device.utils.Math;

import java.math.RoundingMode;

/**
 * Created by Bartosz Osipiuk on 2018-03-16.
 *
 * @author Bartosz Osipiuk
 */

public class Product {
    private String name;
    private Double price;
    private String barcode;

    public Product(String name, Double price, String barcode) {
        this.name = name;
        this.price = price;
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return Math.round(price, 2, RoundingMode.FLOOR);
    }

    public String getBarcode() {
        return barcode;
    }

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
