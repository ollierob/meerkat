package net.meerkat.instrument.fx.forward;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.NamedInstrument;
import net.meerkat.instrument.fx.FxInstrument;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.utils.Require;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class FxForward<B extends CurrencyId, C extends CurrencyId>
        extends NamedInstrument
        implements FxInstrument<B, C> {

    private static final long serialVersionUID = 1L;

    @XmlElementRef(name = "base", required = true)
    private Money<B> base;

    @XmlElementRef(name = "counter", required = true)
    private Money<C> counter;

    @XmlElement(name = "settlementDate")
    private LocalDate settlementDate;

    @XmlAttribute(name = "tradeDate")
    private LocalDate tradeDate;

    @Deprecated
    FxForward() {
    }

    public FxForward(
            final String name,
            final InstrumentIds identifiers,
            final Money<B> base,
            final Money<C> counter,
            final LocalDate settlementDate,
            final LocalDate tradeDate) {
        super(name, identifiers);
        Require.argumentsNotEqual(base.currencyId(), counter.currencyId(), (b, c) -> "Cannot have a forward with base [" + b + "] == counter [" + c + "] currencies!");
        this.base = base;
        this.counter = counter;
        this.settlementDate = settlementDate;
        this.tradeDate = tradeDate;
    }

    public Money<B> baseAmount() {
        return base;
    }

    @Override
    public B base() {
        return base.currencyId();
    }

    public Money<C> counterAmount() {
        return counter;
    }

    @Override
    public C counter() {
        return counter.currencyId();
    }

    @Override
    public ExchangeRate<B, C> exchangeRate() {
        return ExchangeRate.between(base, counter);
    }

    @Override
    public LocalDate settlementDate() {
        return settlementDate;
    }

    @Override
    public LocalDate tradeDate() {
        return tradeDate;
    }

    @Override
    public <R> R handleWith(final FxInstrument.Handler<R> handler) {
        return handler.handle(this);
    }

}
