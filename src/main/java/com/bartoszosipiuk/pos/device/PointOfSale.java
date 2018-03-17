package com.bartoszosipiuk.pos.device;

import com.bartoszosipiuk.pos.device.dao.ProductDAO;
import com.bartoszosipiuk.pos.device.input.BarcodeScanListener;
import com.bartoszosipiuk.pos.device.output.Display;
import com.bartoszosipiuk.pos.device.output.Printer;
import com.bartoszosipiuk.pos.device.product.Product;
import com.bartoszosipiuk.pos.device.product.ShoppingBasket;

/**
 * Created by Bartosz Osipiuk on 2018-03-16.
 *
 * @author Bartosz Osipiuk
 */

public class PointOfSale implements BarcodeScanListener{
    public enum SpecialBarcodes{
        EXIT("exit");

        private final String barcode;
        SpecialBarcodes(String barcode) {
            this.barcode = barcode;
        }

        @Override
        public String toString() {
            return barcode;
        }
    }

    public enum SpecialMessages {
        INVALID_BARCODE("Invalid bar-code"),
        PRODUCT_NOT_FOUND("Product not found");

        private final String message;
        SpecialMessages(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return message;
        }
    }

    private boolean isOrderActive = false;


    private Display display;
    private Printer printer;
    private ProductDAO productDAO;
    private ShoppingBasket shoppingBasket;

    public PointOfSale(Display display, Printer printer, ProductDAO productDAO) {
        this.display = display;
        this.printer = printer;
        this.productDAO = productDAO;
        shoppingBasket = new ShoppingBasket();
    }

    @Override
    public synchronized void onBarcodeScan(String barcode) {
        if(!isValidCode(barcode)){
            handleInvalidCode();
            return;
        }else if (barcode.toLowerCase().equals(SpecialBarcodes.EXIT.toString().toLowerCase())) {
            handleExitCode();
            return;
        }
        Product foundProduct = productDAO.findProduct(barcode);
        if(foundProduct == null) {
            handleProductNotFound();
        } else {
            handleProductFound(foundProduct);
        }
    }

    public boolean isOrderActive() {
        return isOrderActive;
    }

    public int getQuantityOfItemsInBasket(){
        return shoppingBasket.getListOfProducts().size();
    }

    private void handleExitCode() {
        if(!isOrderActive) {
            return;
        }
        printer.print(shoppingBasket.toString());
        display.display(shoppingBasket.getSumOfProductPriceToDisplay());
        shoppingBasket = new ShoppingBasket();
        isOrderActive = false;
    }

    private void handleInvalidCode() {
        display.display(SpecialMessages.INVALID_BARCODE.toString());
    }

    private void handleProductNotFound() {
        display.display(SpecialMessages.PRODUCT_NOT_FOUND.toString());
    }
    private void handleProductFound(Product p) {
        isOrderActive = true;
        display.display(p.getName()+" "+p.getPrice());
        shoppingBasket.add(p);
    }


    private boolean isValidCode(String barcode){
        return !(barcode == null || barcode.isEmpty());
    }
}
