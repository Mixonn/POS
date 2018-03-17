package com.bartoszosipiuk.pos.device.input;

/**
 * Created by Bartosz Osipiuk on 2018-03-17.
 *
 * @author Bartosz Osipiuk
 */

public class ListenerAddedTwiceException extends Exception {
    public ListenerAddedTwiceException() {
        super();
    }

    public ListenerAddedTwiceException(String message) {
        super(message);
    }

    public ListenerAddedTwiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ListenerAddedTwiceException(Throwable cause) {
        super(cause);
    }

    protected ListenerAddedTwiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
