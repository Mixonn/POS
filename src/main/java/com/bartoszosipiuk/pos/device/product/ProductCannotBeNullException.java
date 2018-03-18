package com.bartoszosipiuk.pos.device.product;

/**
 * Created by Bartosz Osipiuk on 2018-03-18.
 *
 * @author Bartosz Osipiuk
 */

public class ProductCannotBeNullException extends Exception{
    public ProductCannotBeNullException() {
        super();
    }

    public ProductCannotBeNullException(String message) {
        super(message);
    }

    public ProductCannotBeNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductCannotBeNullException(Throwable cause) {
        super(cause);
    }

    protected ProductCannotBeNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
