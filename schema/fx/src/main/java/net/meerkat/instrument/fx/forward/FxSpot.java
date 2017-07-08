package net.meerkat.instrument.fx.forward;

import java.time.LocalDate;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.fx.FxInstrument;
import net.meerkat.money.Money;

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

    public FxSpot(
            final String name,
            final InstrumentIds identifiers,
            final Money<B> base,
            final Money<C> counter,
            final LocalDate settlementDate) {
        super(name, identifiers, base, counter, settlementDate);
    }

    @Override
    public <R> R handleWith(final FxInstrument.Handler<R> handler) {
        return handler.handle(this);
    }

}
