package com.bartoszosipiuk.pos.device.product;

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
        return price;
    }

    public String getBarcode() {
        return barcode;
    }

    @Override
    public String toString() {
        return name + '\t' + price;
    }
}
