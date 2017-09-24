package net.meerkat.identifier.currency;

/**
 *
 * @author ollie
 */
public class UnknownCurrencyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final CurrencyId id;

    public UnknownCurrencyException(final CurrencyId id) {
        this.id = id;
    }

}
