package net.meerkat.identifier.currency.crypto;

/**
 *
 * @author ollie
 */
public class ZEC implements CryptoCurrencyId {

    ZEC() {
    }

    @Override
    public String symbol() {
        return "Z";
    }

    @Override
    public String name() {
        return "Zcash";
    }

}
