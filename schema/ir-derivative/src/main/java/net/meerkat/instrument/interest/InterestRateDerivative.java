package net.meerkat.instrument.interest;

import net.meerkat.instrument.InstrumentDefinition;
import net.meerkat.instrument.interest.future.InterestRateFuture;
import net.meerkat.instrument.interest.swap.InterestRateSwap;

/**
 *
 * @author ollie
 */
public interface InterestRateDerivative extends InstrumentDefinition {

    @Override
    default <R> R handleWith(final InstrumentDefinition.Handler<R> handler) {
        return handler instanceof Handler
                ? this.handleWith((Handler<R>) handler)
                : handler.handle(this);
    }

    <R> R handleWith(Handler<R> handler);

    interface Handler<R> extends InstrumentDefinition.Handler<R> {

        R handle(InterestRateFuture<?, ?> future);

        R handle(InterestRateSwap swap);

    }

}
