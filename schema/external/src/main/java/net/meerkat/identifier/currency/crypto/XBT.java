package net.meerkat.identifier.currency.crypto;

import net.meerkat.identifier.currency.AbstractCurrencyIso;

/**
 *
 * @author ollie
 */
public class XBT extends AbstractCurrencyIso implements CryptoCurrencyIso {

    private static final long serialVersionUID = 1L;

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
