package net.meerkat.identifier.currency.crypto;

/**
 *
 * @author ollie
 */
public class XMR implements CryptoCurrencyId {

    XMR() {
    }

    @Override
    public String symbol() {
        return "ɱ";
    }

    @Override
    public String name() {
        return "Monero";
    }

}
