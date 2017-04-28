package net.meerkat.identifier.currency;

import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.money.Money;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class GBP extends NationalCurrencyIso {

    private static final long serialVersionUID = 1L;

    public static final Money<GBP> ONE_HUNDRED_THOUSAND = Money.of(100_000, GBP);

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
