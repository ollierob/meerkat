package net.meerkat.instrument.interest;

import net.meerkat.instrument.interest.future.BondFuture;
import net.meerkat.instrument.interest.future.InterestRateFuture;
import net.meerkat.instrument.interest.future.InterestRateFutureOption;
import net.meerkat.instrument.interest.option.InterestRateOption;
import net.meerkat.instrument.interest.option.Swaption;
import net.meerkat.instrument.interest.swap.InterestRateSwap;
import net.meerkat.instrument.InstrumentDefinition;

/**
 *
 * @author Ollie
 */
public interface InterestRateDerivative extends InstrumentDefinition {

    @Override
    default <R> R handleWith(final InstrumentDefinition.Handler<R> handler) {
        return handler instanceof InterestRateDerivative.Handler
                ? this.handleWith((InterestRateDerivative.Handler<R>) handler)
                : handler.handle(this);
    }

    <R> R handleWith(InterestRateDerivative.Handler<R> handler);

    interface Handler<R> extends InstrumentDefinition.Handler<R> {

        R handle(InterestRateOption option);

        R handle(InterestRateFuture future);

        R handle(BondFuture future);

        R handle(InterestRateFutureOption futureOption);

        R handle(InterestRateSwap swap);

        R handle(Swaption swaption);

    }

}
