package net.ollie.meerkat.identifier.currency;

/**
 *
 * @author ollie
 */
public class RUB extends AbstractCurrencyIso {

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
