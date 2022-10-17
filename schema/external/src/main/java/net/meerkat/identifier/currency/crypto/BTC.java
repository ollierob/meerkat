package net.meerkat.identifier.currency.crypto;

/**
 * @author ollie
 */
public class BTC implements CryptoCurrencyId {

    BTC() {
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
