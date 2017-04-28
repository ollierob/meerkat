package net.meerkat.instrument.fx;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.CurrencyIdPair;
import net.meerkat.instrument.InstrumentDefinition;
import net.meerkat.instrument.dates.Settled;
import net.meerkat.instrument.dates.Traded;
import net.meerkat.instrument.fx.forward.FxForward;
import net.meerkat.instrument.fx.forward.FxSpot;
import net.meerkat.money.fx.ExchangeRate;

/**
 *
 * @author Ollie
 */
public interface FxInstrument<B extends CurrencyId, C extends CurrencyId>
        extends InstrumentDefinition, CurrencyIdPair<B, C>, Traded, Settled {

    @Nonnull
    ExchangeRate<B, C> exchangeRate();

    @Override
    default <R> R handleWith(final InstrumentDefinition.Handler<R> handler) {
        return handler instanceof FxInstrument.Handler
                ? this.handleWith((FxInstrument.Handler<R>) handler)
                : handler.handle(this);
    }

    <R> R handleWith(FxInstrument.Handler<R> handler);

    interface Handler<R>
            extends InstrumentDefinition.Handler<R> {

        R handle(FxForward<?, ?> forward);

        default R handle(final FxSpot<?, ?> spot) {
            return this.handle((FxForward<?, ?>) spot);
        }

    }

}