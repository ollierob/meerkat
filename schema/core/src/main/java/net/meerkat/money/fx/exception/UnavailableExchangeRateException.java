package net.meerkat.money.fx.exception;

import net.meerkat.collections.Arrays;

/**
 *
 * @author ollie
 */
public class UnavailableExchangeRateException extends ExchangeRateException {

    private static final long serialVersionUID = 1L;

    public UnavailableExchangeRateException(final Object... keys) {
        super("Not available for " + Arrays.toString(keys) + "!");
    }

}
