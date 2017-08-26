package net.meerkat.identifier.currency.crypto;

/**
 *
 * @author ollie
 */
public class XBT implements CryptoCurrencyId {

    XBT() {
    }

    @Override
    public String symbol() {
        return "Ƀ";
    }

    @Override
    public String name() {
        return "Bitcoin";
    }

}
