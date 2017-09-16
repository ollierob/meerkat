package net.meerkat.identifier.currency;

/**
 *
 * @author ollie
 */
public class HKD extends NationalCurrencyIso {

    HKD() {
    }

    @Override
    public String symbol() {
        return "$";
    }

    @Override
    public String name() {
        return "Hong Kong Dollar";
    }

}
