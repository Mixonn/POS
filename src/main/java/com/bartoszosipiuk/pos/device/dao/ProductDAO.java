package com.bartoszosipiuk.pos.device.dao;

import com.bartoszosipiuk.pos.device.product.Product;

public interface ProductDAO {
    Product findProduct(String barcode);
}
