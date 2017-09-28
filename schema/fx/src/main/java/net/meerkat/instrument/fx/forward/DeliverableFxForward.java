package net.meerkat.instrument.fx.forward;

import java.time.LocalDate;

import net.meerkat.Explainable;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.NamedInstrument;
import net.meerkat.instrument.fx.FxInstrument;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.time.calendar.settlement.SettlementDate;
import net.meerkat.utils.Require;

/**
 *
 *
 * @author Ollie
 */
public abstract class DeliverableFxForward<B extends CurrencyId, C extends CurrencyId>
        extends NamedInstrument
        implements FxInstrument<B, C> {

    private final Money<B> base;
    private final Money<C> counter;
    private final LocalDate tradeDate;
    private final SettlementDate settlementDate;

    public DeliverableFxForward(
            final String name,
            final InstrumentIds identifiers,
            final Money<B> base,
            final Money<C> counter,
            final LocalDate tradeDate,
            final SettlementDate settlementDate) {
        super(name, identifiers);
        Require.argumentsNotEqual(base.currencyId(), counter.currencyId(), (b, c) -> "Cannot have a forward with base [" + b + "] == counter [" + c + "] currencies!");
        this.base = base;
        this.counter = counter;
        this.tradeDate = tradeDate;
        this.settlementDate = settlementDate;
    }

    public Money<B> baseAmount() {
        return base;
    }

    @Override
    public B baseCurrencyId() {
        return base.currencyId();
    }

    public Money<C> counterAmount() {
        return counter;
    }

    @Override
    public C counterCurrencyId() {
        return counter.currencyId();
    }

    public ExchangeRate<B, C> forwardRate() {
        return ExchangeRate.between(base, counter);
    }

    @Override
    public LocalDate tradeDate() {
        return tradeDate;
    }

    @Override
    public SettlementDate settlementDate() {
        return settlementDate;
    }

    @Override
    public Explainable.ExplanationBuilder explain() {
        return super.explain()
                .put("base amount", base)
                .put("counter amount", counter)
                .put("settlement date", settlementDate)
                .put("trade date", tradeDate);
    }

}
