package net.ollie.meerkat.security.fx.option;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.identifier.security.SecurityIds;
import net.ollie.meerkat.numeric.DecimalFraction;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.numeric.money.fx.ExchangeRate;
import net.ollie.meerkat.security.NamedSecurity;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class FxOptionRate<C extends CurrencyId, P extends CurrencyId>
        extends NamedSecurity
        implements ExchangeRate<C, P> {

    @XmlElementRef(name = "call", required = true)
    private Money<C> call;

    @XmlElementRef(name = "put", required = true)
    private Money<P> put;

    @Deprecated
    FxOptionRate() {
    }

    public FxOptionRate(
            final String name,
            final SecurityIds identifiers,
            final Money<C> call,
            final Money<P> put) {
        super(name, identifiers);
        this.call = call;
        this.put = put;
    }

    @Nonnull
    public Money<C> call() {
        return call;
    }

    @Override
    public C from() {
        return call.currencyId();
    }

    @Nonnull
    public Money<P> put() {
        return put;
    }

    @Override
    public P to() {
        return put.currencyId();
    }

    @Override
    public DecimalFraction rate() {
        return DecimalFraction.of(put.amount(), call.amount());
    }

}
