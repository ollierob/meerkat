package net.meerkat.instrument.equity.swap;

import net.meerkat.instrument.equity.Equity;
import net.meerkat.instrument.equity.EquityDerivative;
import net.meerkat.money.interest.HasInterestRateId;
import net.meerkat.money.interest.InterestRateId;

/**
 *
 * @author ollie
 */
public interface FloatingInterestForEquitySwap<E extends Equity>
        extends EquitySwap<E>, HasInterestRateId {

    @Override
    InterestRateId interestRateId();

    @Override
    default <R> R handleWith(final EquityDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

}
