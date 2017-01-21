package net.ollie.meerkat.identifier.currency;

/**
 *
 * @author ollie
 */
public class RUB extends NationalCurrencyIso {

    private static final long serialVersionUID = 1L;

    RUB() {
    }

    @Override
    public String symbol() {
        return "₽";
    }

    @Override
    public String name() {
        return "Russian ruble";
    }

}
