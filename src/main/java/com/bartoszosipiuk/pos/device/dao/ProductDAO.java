package com.bartoszosipiuk.pos.device.dao;

import com.bartoszosipiuk.pos.device.product.Product;

/**
 * This interface can be used with class, that connect to your DataBase with {@link Product Products}.
 * The {@link com.bartoszosipiuk.pos.device.PointOfSale PointOfSale}
 * class uses this interface as constructor argument}
 *
 * @author Bartosz Osipiuk
 */
public interface ProductDAO {
    /**
     * This method is used to check does the {@link Product Product} exists in the database,
     * and if so - creates it and returns.
     * @param barcode The barcode of searched {@link Product product}
     * @return Returns {@link Product Product} if found in DB, else returns null
     */
    Product findProduct(String barcode);
}
