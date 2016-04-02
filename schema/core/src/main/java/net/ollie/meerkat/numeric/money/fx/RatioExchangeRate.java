package net.ollie.meerkat.numeric.money.fx;

import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.DecimalFraction;
import net.ollie.meerkat.numeric.money.Money;

/**
 *
 * @author Ollie
 */
public class RatioExchangeRate<F extends CurrencyId, T extends CurrencyId> implements ExchangeRate<F, T> {

    @XmlElementRef(name = "from")
    private Money<F> from;

    @XmlElementRef(name = "to")
    private Money<T> to;

    @Deprecated
    RatioExchangeRate() {
    }

    public RatioExchangeRate(final Money<F> from, final Money<T> to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public F from() {
        return from.currencyId();
    }

    @Override
    public T to() {
        return to.currencyId();
    }

    @Override
    public DecimalFraction rate() {
        return DecimalFraction.of(to.amount(), from.amount());
    }

}
