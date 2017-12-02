package net.meerkat.instrument.fx.forward;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.NamedInstrument;
import net.meerkat.instrument.fx.FxInstrument;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.time.calendar.settlement.SettlementDate;

import java.time.LocalDate;

/**
 * @author ollie
 */
public class NonDeliverableFxForward<B extends CurrencyId, C extends CurrencyId>
        extends NamedInstrument
        implements FxInstrument<B, C> {

    private final Money<C> notional;
    private final ExchangeRate<B, C> spotRate;
    private final LocalDate fixingDate;
    private final SettlementDate settlementDate;
    private final LocalDate tradeDate;

    public NonDeliverableFxForward(
            final String name,
            final InstrumentIds ids,
            final Money<C> notional,
            final ExchangeRate<B, C> spotRate,
            final LocalDate fixingDate,
            final SettlementDate settlementDate,
            final LocalDate tradeDate) {
        super(name, ids);
        this.notional = notional;
        this.spotRate = spotRate;
        this.fixingDate = fixingDate;
        this.settlementDate = settlementDate;
        this.tradeDate = tradeDate;
    }

    public Money<C> notionalAmount() {
        return notional;
    }

    public ExchangeRate<B, C> spotRate() {
        return spotRate;
    }

    @Override
    public B baseCurrencyId() {
        return spotRate.from();
    }

    @Override
    public C counterCurrencyId() {
        return spotRate.to();
    }

    /**
     * @return the date on which the difference between the market and agreed exchange rates is calculated.
     */
    public LocalDate fixingDate() {
        return fixingDate;
    }

    @Override
    public SettlementDate settlementDate() {
        return settlementDate;
    }

    @Override
    public LocalDate tradeDate() {
        return tradeDate;
    }

    @Override
    public <R> R handleWith(final Handler<R> handler) {
        return handler.handle(this);
    }

}
