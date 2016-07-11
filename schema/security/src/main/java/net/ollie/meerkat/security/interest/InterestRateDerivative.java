package net.ollie.meerkat.security.interest;

import net.ollie.meerkat.security.SecurityDefinition;
import net.ollie.meerkat.security.interest.future.BondFuture;
import net.ollie.meerkat.security.interest.future.InterestRateFuture;
import net.ollie.meerkat.security.interest.future.InterestRateFutureOption;
import net.ollie.meerkat.security.interest.option.InterestRateOption;
import net.ollie.meerkat.security.interest.option.Swaption;
import net.ollie.meerkat.security.interest.swap.InterestRateSwap;

/**
 *
 * @author Ollie
 */
public interface InterestRateDerivative extends SecurityDefinition {

    @Override
    default <R> R handleWith(final SecurityDefinition.Handler<R> handler) {
        return handler instanceof InterestRateDerivative.Handler
                ? this.handleWith((InterestRateDerivative.Handler<R>) handler)
                : handler.handle(this);
    }

    <R> R handleWith(InterestRateDerivative.Handler<R> handler);

    interface Handler<R> extends SecurityDefinition.Handler<R> {

        R handle(InterestRateOption option);

        R handle(InterestRateFuture future);

        R handle(BondFuture future);

        R handle(InterestRateFutureOption futureOption);

        R handle(InterestRateSwap swap);

        R handle(Swaption swaption);

    }

}
