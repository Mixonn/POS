package com.bartoszosipiuk.pos.device;

import com.bartoszosipiuk.pos.device.PointOfSale.SpecialMessages;
import com.bartoszosipiuk.pos.device.dao.ProductDAO;
import com.bartoszosipiuk.pos.device.output.Display;
import com.bartoszosipiuk.pos.device.output.Printer;
import com.bartoszosipiuk.pos.device.product.Product;
import com.bartoszosipiuk.pos.device.product.ShoppingBasket;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class PointOfSaleTest {

    private final String VALID_BARCODE = "XDLE3DE3";

    private Display display;
    private Printer printer;
    private ProductDAO productDAO;
    private ShoppingBasket shoppingBasket;
    private PointOfSale pointOfSale;

    @Before
    public void setUp(){
        display = mock(Display.class);
        printer = mock(Printer.class);
        productDAO = mock(ProductDAO.class);
        pointOfSale = new PointOfSale(display, printer, productDAO);
    }

    @Test
    public void isOrderActive_Test(){
        assertFalse("Order should not be active after initializing PointOfSale",
                pointOfSale.isOrderActive());
        when(productDAO.findProduct(VALID_BARCODE)).thenReturn(new Product("X", 2.41d, VALID_BARCODE));

        pointOfSale.onBarcodeScan(VALID_BARCODE);
        assertTrue("Order should be active after calling onBarcodeScan with valid Barcode",
                pointOfSale.isOrderActive());

        pointOfSale.onBarcodeScan(PointOfSale.SpecialBarcodes.EXIT.toString());
        assertFalse("Order should not be active after \"exit\" barcode",
                pointOfSale.isOrderActive());
    }

    @Test
    public void handleInvalidCode_ShouldDisplayMessage(){
        pointOfSale.onBarcodeScan("");
        pointOfSale.onBarcodeScan(null);
        verify(display, times(2)).display(SpecialMessages.INVALID_BARCODE.toString());
    }

    @Test
    public void handleProductNotFound_ShouldDisplayMessage(){
        pointOfSale.onBarcodeScan(VALID_BARCODE);
        when(productDAO.findProduct(VALID_BARCODE)).thenReturn(null);
        verify(display).display(SpecialMessages.PRODUCT_NOT_FOUND.toString());
    }

    @Test
    public void handleExitCode_ShouldNotDisplayAnythingIfOrderIsNotActive(){
        pointOfSale.onBarcodeScan(PointOfSale.SpecialBarcodes.EXIT.toString());
        verifyZeroInteractions(printer);
        verifyZeroInteractions(display);
        verifyZeroInteractions(productDAO);
    }

    @Test
    public void handleExitCode_ShouldDisplayAndPrintReceipt(){
        when(productDAO.findProduct(VALID_BARCODE)).thenReturn(new Product("X", 2.41d, VALID_BARCODE));
        pointOfSale.onBarcodeScan(VALID_BARCODE);
        pointOfSale.onBarcodeScan(PointOfSale.SpecialBarcodes.EXIT.toString());
        verify(printer).print(anyString());
        verify(display, atLeast(1)).display(anyString());
        verify(display, atMost(2)).display(anyString());
    }

    @Test
    public void handleExitCode_ShouldClearTheBasket(){
        when(productDAO.findProduct(VALID_BARCODE)).thenReturn(new Product("X", 2.41d, VALID_BARCODE));
        pointOfSale.onBarcodeScan(VALID_BARCODE);
        assertEquals("Basket should contains item after calling handleProductFound",
                1, pointOfSale.getQuantityOfItemsInBasket());
        pointOfSale.onBarcodeScan(PointOfSale.SpecialBarcodes.EXIT.toString());
        assertEquals(0, pointOfSale.getQuantityOfItemsInBasket());
    }


    @Test
    public void handleProductFound_ShouldDisplayNameAndPrice(){
        Product p = new Product("TEMPNAME", 3.23, VALID_BARCODE);
        when(productDAO.findProduct(VALID_BARCODE)).thenReturn(p);
        pointOfSale.onBarcodeScan(VALID_BARCODE);
        verify(display).display(p.getName()+" "+p.getPrice());
    }

    @Test
    public void handleProductFound_ShouldAddProductToShoppingBasket(){
        assertEquals("Basket should be empty after initializing PointOfSale",
                0, pointOfSale.getQuantityOfItemsInBasket());
        when(productDAO.findProduct(VALID_BARCODE)).thenReturn(new Product("TEMPNAME", 3.23, VALID_BARCODE));
        pointOfSale.onBarcodeScan(VALID_BARCODE);
        assertEquals("Basket should contains item after calling handleProductFound",
                1, pointOfSale.getQuantityOfItemsInBasket());
    }





}