package net.ollie.meerkat.identifier.currency;

/**
 *
 * @author Ollie
 */
public interface CurrencyId extends HasCurrencyId {

    @Override
    default CurrencyId currencyId() {
        return this;
    }

}
