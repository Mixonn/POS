package com.bartoszosipiuk.pos.device;

import com.bartoszosipiuk.pos.device.dao.ProductDAO;
import com.bartoszosipiuk.pos.device.input.BarcodeScanListener;
import com.bartoszosipiuk.pos.device.output.Display;
import com.bartoszosipiuk.pos.device.output.Printer;
import com.bartoszosipiuk.pos.device.product.Product;
import com.bartoszosipiuk.pos.device.product.ProductCannotBeNullException;
import com.bartoszosipiuk.pos.device.product.ShoppingBasket;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Bartosz Osipiuk on 2018-03-16.
 *
 * @author Bartosz Osipiuk
 */

public class PointOfSale implements BarcodeScanListener{
    private static final Logger LOGGER = Logger.getLogger( PointOfSale.class.getName() );
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
        }else if (barcode.equalsIgnoreCase(SpecialBarcodes.EXIT.toString())) {
            handleExitCode();
            return;
        }
        Product foundProduct = productDAO.findProduct(barcode);
        if(foundProduct == null) {
            handleProductNotFound();
        } else {
            try {
                handleProductFound(foundProduct);
            } catch (ProductCannotBeNullException e) {
                LOGGER.log( Level.SEVERE, e.toString(), e );
            }
        }
    }

    /**
     * Tells that is order active or not. After exit barcode we set isOrderActive to false;
     * @return Returns isOrderActive
     */
    public boolean isOrderActive() {
        return isOrderActive;
    }

    /**
     * Returns how much items the basket contains
     * @return Returns how much items the basket contains
     */
    public int getQuantityOfItemsInBasket(){
        return shoppingBasket.getListOfProducts().size();
    }

    /**
     * Handles exit code. If order was active - lets the printer and display to show information about the order,
     * clears the basket and sets isOrderActive to false
     * If order was not active - returns
     */
    private void handleExitCode() {
        if(!isOrderActive) {
            return;
        }
        printer.print(shoppingBasket.toString());
        display.display(shoppingBasket.getSumOfProductPriceToDisplay());
        shoppingBasket = new ShoppingBasket();
        isOrderActive = false;
    }

    /**
     * Handle invalid code by displaying {@link SpecialMessages#INVALID_BARCODE INVALID_BARCODE} message.
     */
    private void handleInvalidCode() {
        display.display(SpecialMessages.INVALID_BARCODE.toString());
    }

    /**
     * Handle product not found by displaying {@link SpecialMessages#PRODUCT_NOT_FOUND PRODUCT_NOT_FOUND} message.
     */
    private void handleProductNotFound() {
        display.display(SpecialMessages.PRODUCT_NOT_FOUND.toString());
    }

    /**
     * Handles the found product. Sets isOrderActive to true, displays name and price of product and adds it to basket.
     * @param p The product to handle
     * @throws ProductCannotBeNullException when parameter is null
     */
    private void handleProductFound(Product p) throws ProductCannotBeNullException {
        isOrderActive = true;
        display.display(p.getName()+" "+p.getPrice());
        shoppingBasket.add(p);
    }


    /**
     * Check is the code is valid or not.
     * @param barcode Barcode to chceck
     * @return false if is null or empty, true if is not.
     */
    private boolean isValidCode(String barcode){
        return !(barcode == null || barcode.isEmpty());
    }
}
