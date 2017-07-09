package net.meerkat.instrument.interest.swap;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.HasCurrencyIds;
import net.meerkat.instrument.derivative.swap.SwapLeg;
import net.meerkat.money.interest.InterestRateOrId;

/**
 *
 * @author ollie
 */
public interface InterestRateSwapLeg<P extends CurrencyId, R extends CurrencyId>
        extends SwapLeg<P, R>, HasCurrencyIds {

    @Nonnull
    InterestRateOrId payRate();

    @Nonnull
    InterestRateOrId receiveRate();

}
