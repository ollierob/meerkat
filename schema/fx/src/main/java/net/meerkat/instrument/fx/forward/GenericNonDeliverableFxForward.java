package net.meerkat.instrument.fx.forward;

import java.time.LocalDate;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.NamedInstrument;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.time.calendar.settlement.SettlementDate;

/**
 *
 * @author ollie
 */
public class GenericNonDeliverableFxForward<B extends CurrencyId, C extends CurrencyId>
        extends NamedInstrument
        implements NonDeliverableFxForward<B, C> {

    private final Money<C> notional;
    private final ExchangeRate<B, C> spotRate;
    private final LocalDate fixingDate;
    private final SettlementDate settlementDate;
    private final LocalDate tradeDate;

    public GenericNonDeliverableFxForward(
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

    @Override
    public Money<C> notionalAmount() {
        return notional;
    }

    @Override
    public ExchangeRate<B, C> spotRate() {
        return spotRate;
    }

    @Override
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

}
