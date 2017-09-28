package net.meerkat.identifier.currency.crypto;

/**
 *
 * @author ollie
 */
public class DASH implements CryptoCurrencyId {

    DASH() {
    }

    @Override
    public String symbol() {
        return "DASH";
    }

    @Override
    public String name() {
        return "Dash";
    }

}
