package net.meerkat.identifier.currency;


import net.meerkat.money.Money;

/**
 *
 * @author Ollie
 */
public class GBP extends NationalCurrencyIso {

    private static final long serialVersionUID = 1L;

    public static final Money<GBP> ONE_HUNDRED_THOUSAND = Money.of(GBP, 100_000);

    GBP() {
    }

    @Override
    public String symbol() {
        return "£";
    }

    @Override
    public String name() {
        return "Pound Sterling";
    }

}
