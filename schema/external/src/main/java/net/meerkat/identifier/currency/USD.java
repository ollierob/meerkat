package net.meerkat.identifier.currency;

import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.money.Money;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class USD extends NationalCurrencyIso {

    public static final Money<USD> ONE_MILLION = Money.of(1_000_000, USD);

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
