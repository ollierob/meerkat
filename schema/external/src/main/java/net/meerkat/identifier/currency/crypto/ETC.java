package net.meerkat.identifier.currency.crypto;

/**
 *
 * @author ollie
 */
public class ETC implements CryptoCurrencyId {

    ETC() {
    }

    @Override
    public String symbol() {
        return "E";
    }

    @Override
    public String name() {
        return "Ethereum Classic";
    }

}
