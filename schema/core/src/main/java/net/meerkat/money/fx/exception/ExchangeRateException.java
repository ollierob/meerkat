package net.meerkat.money.fx.exception;

/**
 *
 * @author ollie
 */
public abstract class ExchangeRateException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    protected ExchangeRateException(final String message) {
        super(message);
    }

}
