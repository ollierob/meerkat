package net.meerkat.instrument.fx.forward;

import java.time.LocalDate;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.money.Money;
import net.meerkat.instrument.fx.FxInstrument;

/**
 *
 * @author ollie
 */
public class FxSpot<B extends CurrencyId, C extends CurrencyId>
        extends FxForward<B, C> {

    private static final long serialVersionUID = 1L;

    @Deprecated
    FxSpot() {
    }

    public FxSpot(String name, InstrumentIds identifiers, Money<B> base, Money<C> counter, LocalDate settlementDate, LocalDate tradeDate) {
        super(name, identifiers, base, counter, settlementDate, tradeDate);
    }

    @Override
    public <R> R handleWith(final FxInstrument.Handler<R> handler) {
        return handler.handle(this);
    }

}
