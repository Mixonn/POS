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
        if (barcode.toLowerCase().equals(SpecialBarcodes.EXIT.toString().toLowerCase())) {
            handleExitCode();
        }
        if(!isValidCode(barcode)){
            handleInvalidCode();
        }
        Product foundProduct = productDAO.findProduct(barcode);
        if(foundProduct == null) {
            handleProductNotFound();
        } else {
            handleProductFound(foundProduct);
        }
    }
    private void handleExitCode() {
        if(!isOrderActive) {
            return;
        }
        printer.print(shoppingBasket.toString());
        display.display(shoppingBasket.getSumOfProductPriceToDisplay());
        shoppingBasket = new ShoppingBasket();
    }

    private void handleInvalidCode() {
        display.display("Invalid bar-code");
    }

    private void handleProductNotFound() {
        display.display("Product not found");
    }
    private void handleProductFound(Product p) {
        display.display(p.getName()+" "+p.getPrice());
        shoppingBasket.add(p);
    }


    private boolean isValidCode(String barcode){
        return barcode == null || barcode.isEmpty();
    }
}
