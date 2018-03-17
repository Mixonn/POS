package com.bartoszosipiuk.pos.device.product;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Bartosz Osipiuk on 2018-03-16.
 *
 * @author Bartosz Osipiuk
 */

public class ShoppingBasket {
    private List<Product> products;
    private Double sumOfProductPrice;

    public ShoppingBasket() {
        this.products = new LinkedList<>();
        this.sumOfProductPrice = 0d;
    }
    
    public void add(Product product){
        if(product == null){
            return;
        }
        products.add(product);
        sumOfProductPrice+=product.getPrice();
    }

    public String getSumOfProductPriceToDisplay() {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.FLOOR);
        return df.format(sumOfProductPrice);
    }

    public Double getSumOfProductPrice() {
        return sumOfProductPrice;
    }

    public List<Product> getListOfProducts(){
        return Collections.unmodifiableList(products);
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.FLOOR);
        StringBuilder sb = new StringBuilder();
        sb.append("Name\tPrice");
        products.forEach(p -> sb.append(p).append("\n"));
        sb.append("Total price: ").append(df.format(sumOfProductPrice));
        return sb.toString();
    }
}
