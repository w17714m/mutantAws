package com.mercadolibre.domain.excepcion;

public class DnaException extends Exception {
    public DnaException() {
    }

    public DnaException(String message) {
        super(message);
    }

    public DnaException(String message, Throwable cause) {
        super(message, cause);
    }

    public DnaException(Throwable cause) {
        super(cause);
    }

    public DnaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
