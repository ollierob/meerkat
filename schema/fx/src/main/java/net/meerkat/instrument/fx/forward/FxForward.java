package net.meerkat.instrument.fx.forward;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.NamedInstrument;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRate;
import net.ollie.goat.temporal.date.interim.CompleteInterval;
import net.meerkat.utils.Require;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.fx.FxInstrumentDefinition;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class FxForward
        extends NamedInstrument
        implements FxInstrumentDefinition {

    private static final long serialVersionUID = 1L;

    @XmlElementRef(name = "base")
    private Money<?> base;

    @XmlElementRef(name = "counter")
    private Money<?> counter;

    @XmlAttribute(name = "settlement")
    private CompleteInterval settlement;

    @Deprecated
    FxForward() {
    }

    public FxForward(
            final String name,
            final InstrumentIds identifiers,
            final Money<?> base,
            final Money<?> counter,
            final CompleteInterval settlement) {
        super(name, identifiers);
        Require.that(base.currencyId() != counter.currencyId(), () -> "Cannot have a forward using [" + base + "] == [" + counter + "]!");
        this.base = base;
        this.counter = counter;
        this.settlement = settlement;
    }

    public Money<?> baseAmount() {
        return base;
    }

    @Override
    public CurrencyId base() {
        return base.currencyId();
    }

    public Money<?> counterAmount() {
        return counter;
    }

    @Override
    public CurrencyId counter() {
        return counter.currencyId();
    }

    @Override
    public ExchangeRate<?, ?> exchangeRate() {
        return ExchangeRate.between(base, counter);
    }

    @Override
    public CompleteInterval settlementDate() {
        return settlement;
    }

    @Override
    public LocalDate tradeDate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <R> R handleWith(final FxInstrumentDefinition.Handler<R> handler) {
        return handler.handle(this);
    }

}
