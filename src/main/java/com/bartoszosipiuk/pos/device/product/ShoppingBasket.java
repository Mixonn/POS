package com.bartoszosipiuk.pos.device.product;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * The shopping basket contains {@link List List} of the products and holds the sum of all
 *
 * @author Bartosz Osipiuk
 */

public class ShoppingBasket {
    private List<Product> products;
    private Double sumOfProductPrice;

    /**
     * Shopping basket constructor. Creates new products list and sets the price sum to 0;
     */
    public ShoppingBasket() {
        this.products = new LinkedList<>();
        this.sumOfProductPrice = 0d;
    }

    /**
     * Adds the product to products list if the product is not null.
     * If product is null - throws {@link ProductCannotBeNullException ProductCannotBeNullException}.
     * @param product Product to add
     * @throws ProductCannotBeNullException if product is null
     */
    public void add(Product product) throws ProductCannotBeNullException{
        if(product == null){
            throw new ProductCannotBeNullException("The product cannot be null");
        }
        products.add(product);
        sumOfProductPrice+=product.getPrice();
    }

    /**
     * Returns rounded (Floor) sum of all product prices to 2 decimal places as formatted string
     * @return Returns rounded (Floor) sum of all product prices to 2 decimal places as formatted string
     */
    public String getSumOfProductPriceToDisplay() {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.FLOOR);
        return df.format(sumOfProductPrice);
    }

    /**
     * Returns sum of all product prices
     * @return Returns sum of all product prices
     */
    public Double getSumOfProductPrice() {
        return sumOfProductPrice;
    }

    /**
     * Returns unmodifiableList of products
     * @return Returns unmodifiableList of products
     */
    public List<Product> getListOfProducts(){
        return Collections.unmodifiableList(products);
    }

    /**
     * Returns formatted string with all products name and price. In the end of the message puts the sum of all items.
     * @return Returns formatted string with all products name and price. In the end of the message puts the sum of all items.
     */
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
