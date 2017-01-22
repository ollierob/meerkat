package net.meerkat.identifier.currency;

/**
 *
 * @author ollie
 */
public class TRY extends NationalCurrencyIso {

    private static final long serialVersionUID = 1L;

    TRY() {
    }

    @Override
    public String symbol() {
        return "₺";
    }

    @Override
    public String name() {
        return "Turkish lira";
    }

}
