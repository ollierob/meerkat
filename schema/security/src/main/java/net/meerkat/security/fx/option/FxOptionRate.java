package net.meerkat.security.fx.option;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.money.currency.Currency;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRate;
import net.ollie.goat.numeric.fraction.DecimalFraction;
import net.meerkat.identifier.security.SecurityIds;
import net.meerkat.security.NamedSecurity;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class FxOptionRate<C extends Currency, P extends Currency>
        extends NamedSecurity
        implements ExchangeRate<C, P> {

    private static final long serialVersionUID = 1L;

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
        return call.currency();
    }

    @Nonnull
    public Money<P> put() {
        return put;
    }

    @Override
    public P to() {
        return put.currency();
    }

    @Override
    public DecimalFraction rate() {
        return DecimalFraction.of(put.amount(), call.amount());
    }

}
