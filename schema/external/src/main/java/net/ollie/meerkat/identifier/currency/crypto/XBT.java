package net.ollie.meerkat.identifier.currency.crypto;

import net.ollie.meerkat.identifier.currency.AbstractCurrencyIso;

/**
 *
 * @author ollie
 */
public class XBT extends AbstractCurrencyIso implements CryptoCurrency {

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
