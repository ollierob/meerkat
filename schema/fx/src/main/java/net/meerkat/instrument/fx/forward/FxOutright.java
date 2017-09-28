package net.meerkat.instrument.fx.forward;

import java.time.LocalDate;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.money.Money;
import net.meerkat.time.calendar.settlement.SettlementDate;

/**
 *
 * @author ollie
 */
public class FxOutright<B extends CurrencyId, C extends CurrencyId>
        extends DeliverableFxForward<B, C> {

    public FxOutright(
            final String name,
            final InstrumentIds identifiers,
            final Money<B> base,
            final Money<C> counter,
            final LocalDate tradeDate,
            final SettlementDate settlementDate) {
        super(name, identifiers, base, counter, tradeDate, settlementDate);
    }

    @Override
    public <R> R handleWith(final Handler<R> handler) {
        return handler.handle(this);
    }

}
