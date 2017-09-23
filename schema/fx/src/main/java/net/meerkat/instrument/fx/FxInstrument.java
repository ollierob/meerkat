package net.meerkat.instrument.fx;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.CurrencyIdPair;
import net.meerkat.identifier.currency.CurrencyIds;
import net.meerkat.instrument.InstrumentDefinition;
import net.meerkat.instrument.dates.Settles;
import net.meerkat.instrument.fx.forward.FxOutright;
import net.meerkat.instrument.fx.forward.FxSpot;
import net.meerkat.instrument.fx.forward.NonDeliverableFxForward;

/**
 *
 * @author Ollie
 */
public interface FxInstrument<B extends CurrencyId, C extends CurrencyId>
        extends InstrumentDefinition, Settles {

    @Nonnull
    B baseCurrencyId();

    @Nonnull
    C counterCurrencyId();

    @Nonnull
    default CurrencyIds currencyIds() {
        return new CurrencyIdPair<>(this.baseCurrencyId(), this.counterCurrencyId());
    }

    @Override
    default <R> R handleWith(final InstrumentDefinition.Handler<R> handler) {
        return handler instanceof FxInstrument.Handler
                ? this.handleWith((FxInstrument.Handler<R>) handler)
                : handler.handle(this);
    }

    <R> R handleWith(FxInstrument.Handler<R> handler);

    interface Handler<R>
            extends InstrumentDefinition.Handler<R> {

        R handle(FxSpot<?, ?> spot);

        R handle(FxOutright<?, ?> forward);

        R handle(NonDeliverableFxForward<?, ?> forward);

    }

}
