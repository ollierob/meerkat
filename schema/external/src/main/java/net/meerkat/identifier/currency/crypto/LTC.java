package net.meerkat.identifier.currency.crypto;

/**
 *
 * @author ollie
 */
public class LTC implements CryptoCurrencyId {

    @Override
    public String symbol() {
        return "Ł";
    }

    @Override
    public String name() {
        return "Litecoin";
    }

}
