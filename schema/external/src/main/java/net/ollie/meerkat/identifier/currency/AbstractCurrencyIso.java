package net.ollie.meerkat.identifier.currency;

/**
 *
 * @author Ollie
 */
abstract class AbstractCurrencyIso implements CurrencyIso {

    private static final long serialVersionUID = 1L;

    @Override
    public String value() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return this.value();
    }

}
