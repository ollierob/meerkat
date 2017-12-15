package net.meerkat.instrument.fx;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.CurrencyIdPair;
import net.meerkat.identifier.currency.CurrencyIds;
import net.meerkat.identifier.currency.HasCurrencyIds;
import net.meerkat.instrument.InstrumentDefinition;
import net.meerkat.instrument.dates.Settles;
import net.meerkat.instrument.fx.forward.FxOutright;
import net.meerkat.instrument.fx.forward.FxSpot;
import net.meerkat.instrument.fx.forward.NonDeliverableFxForward;
import net.meerkat.objects.Castable;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 * @author Ollie
 */
public interface FxInstrument<B extends CurrencyId, C extends CurrencyId>
        extends InstrumentDefinition, HasCurrencyIds, Settles, Castable<FxInstrument<?, ?>> {

    @Nonnull
    B baseCurrencyId();

    @Nonnull
    C counterCurrencyId();

    @Override
    default CurrencyIds currencyIds() {
        return new CurrencyIdPair<>(this.baseCurrencyId(), this.counterCurrencyId());
    }

    @Override
    default <R> R handleWith(final InstrumentDefinition.Handler<R> handler) {
        return handler instanceof FxInstrument.Handler
                ? this.handleWith((FxInstrument.Handler<R>) handler)
                : handler.handleUnknown(this);
    }

    @CheckForNull
    <R> R handleWith(FxInstrument.Handler<R> handler);

    interface Handler<R>
            extends InstrumentDefinition.Handler<R> {

        R handle(FxSpot<?, ?> spot);

        R handle(FxOutright<?, ?> forward);

        R handle(NonDeliverableFxForward<?, ?> forward);

    }

}
