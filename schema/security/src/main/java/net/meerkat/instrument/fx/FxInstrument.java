package net.meerkat.instrument.fx;

import javax.annotation.Nonnull;

import net.meerkat.instrument.InstrumentDefinition;
import net.meerkat.instrument.OtcInstrument;
import net.meerkat.instrument.fx.forward.FxForward;
import net.meerkat.money.currency.CurrencyPair;
import net.meerkat.money.fx.ExchangeRate;
import net.ollie.goat.temporal.date.interim.CompleteInterval;

/**
 *
 * @author Ollie
 */
public interface FxInstrument extends OtcInstrument, InstrumentDefinition, CurrencyPair.Untyped {

    @Nonnull
    ExchangeRate<?, ?> exchangeRate();

    @Nonnull
    CompleteInterval settlementDate();

    @Deprecated
    default FxInstrument instrument() {
        return this;
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

        R handle(FxForward forward);

    }

}
