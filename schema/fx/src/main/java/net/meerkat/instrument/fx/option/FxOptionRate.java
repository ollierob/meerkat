package net.meerkat.instrument.fx.option;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.NamedInstrument;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRate;
import net.ollie.goat.numeric.fraction.BigDecimalFraction;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class FxOptionRate<C extends CurrencyId, P extends CurrencyId>
        extends NamedInstrument
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
            final InstrumentIds identifiers,
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
    public BigDecimalFraction bidRate() {
        return BigDecimalFraction.of(put.amount(), call.amount());
    }

    @Override
    public BigDecimalFraction offerRate() {
        throw new UnsupportedOperationException("Not supported yet."); //TODO
    }

}
