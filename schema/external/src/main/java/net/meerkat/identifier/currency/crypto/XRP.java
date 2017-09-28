package net.meerkat.identifier.currency.crypto;

/**
 *
 * @author ollie
 */
public class XRP implements CryptoCurrencyId {

    XRP() {
    }

    @Override
    public String symbol() {
        return "R";
    }

    @Override
    public String name() {
        return "Ripple";
    }

}
