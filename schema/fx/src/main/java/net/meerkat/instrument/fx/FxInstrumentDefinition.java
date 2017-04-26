package net.meerkat.instrument.fx;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyIdPair;
import net.meerkat.instrument.InstrumentDefinition;
import net.meerkat.instrument.OtcInstrument;
import net.meerkat.instrument.fx.forward.FxForward;
import net.meerkat.money.fx.ExchangeRate;
import net.ollie.goat.temporal.date.interim.CompleteInterval;

/**
 *
 * @author Ollie
 */
public interface FxInstrumentDefinition
        extends OtcInstrument, InstrumentDefinition, CurrencyIdPair.Untyped {

    @Nonnull
    ExchangeRate<?, ?> exchangeRate();

    @Nonnull
    CompleteInterval settlementDate();

    @Deprecated
    default FxInstrumentDefinition instrument() {
        return this;
    }

    @Override
    default <R> R handleWith(final InstrumentDefinition.Handler<R> handler) {
        return handler instanceof FxInstrumentDefinition.Handler
                ? this.handleWith((FxInstrumentDefinition.Handler<R>) handler)
                : handler.handle(this);
    }

    <R> R handleWith(FxInstrumentDefinition.Handler<R> handler);

    interface Handler<R>
            extends InstrumentDefinition.Handler<R> {

        R handle(FxForward forward);

    }

}
