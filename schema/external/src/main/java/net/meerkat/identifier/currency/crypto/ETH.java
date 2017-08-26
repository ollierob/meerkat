package net.meerkat.identifier.currency.crypto;

/**
 *
 * @author Ollie
 */
public class ETH implements CryptoCurrencyId {

    ETH() {
    }

    @Override
    public String symbol() {
        return "Ξ";
    }

    @Override
    public String name() {
        return "Ethereum";
    }

}
