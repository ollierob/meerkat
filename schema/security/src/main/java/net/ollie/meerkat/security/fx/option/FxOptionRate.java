package net.ollie.meerkat.security.fx.option;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.DecimalFraction;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.numeric.money.fx.ExchangeRate;
import net.ollie.meerkat.security.NamedSecurity;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class FxOptionRate<F extends CurrencyId, T extends CurrencyId>
        extends NamedSecurity
        implements ExchangeRate<F, T> {

    @XmlElementRef(name = "call", required = true)
    private Money<F> call;

    @XmlElementRef(name = "put", required = true)
    private Money<T> put;

    @Deprecated
    FxOptionRate() {
    }

    public FxOptionRate(final String name, final Money<F> call, final Money<T> put) {
        super(name);
        this.call = call;
        this.put = put;
    }

    @Nonnull
    public Money<F> call() {
        return call;
    }

    @Override
    public F from() {
        return call.currencyId();
    }

    @Nonnull
    public Money<T> put() {
        return put;
    }

    @Override
    public T to() {
        return put.currencyId();
    }

    @Override
    public DecimalFraction rate() {
        return DecimalFraction.of(put.amount(), call.amount());
    }

}
