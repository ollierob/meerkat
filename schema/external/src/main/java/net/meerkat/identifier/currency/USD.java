package net.meerkat.identifier.currency;

import net.meerkat.money.Money;

/**
 * @author Ollie
 */
public class USD extends NationalCurrencyIso {

    public static final Money<USD> ONE_MILLION = Money.of(USD, 1_000_000);

    private static final long serialVersionUID = 1L;

    USD() {
    }

    @Override
    public String symbol() {
        return "$";
    }

    @Override
    public String name() {
        return "United States dollar";
    }

}
