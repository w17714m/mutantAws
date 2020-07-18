package com.mercadolibre.domain.excepcion;

public class StatsException extends Exception {
    public StatsException() {
        super();
    }

    public StatsException(String message) {
        super(message);
    }

    public StatsException(String message, Throwable cause) {
        super(message, cause);
    }

    public StatsException(Throwable cause) {
        super(cause);
    }

    protected StatsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
