package net.meerkat.instrument.interest.swap;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.CurrencyIds;
import net.meerkat.identifier.currency.HasCurrencyId;
import net.meerkat.instrument.derivative.swap.SwapLegs;
import net.meerkat.instrument.interest.swap.leg.InterestRateSwapLeg;

/**
 *
 * @author Ollie
 */
public interface SingleCurrencyInterestRateSwap<C extends CurrencyId> extends InterestRateSwap, HasCurrencyId {

    @Override
    C currencyId();

    @Override
    SwapLegs.Finite<? extends InterestRateSwapLeg<C, C>> legs();

    @Override
    default CurrencyIds currencyIds() {
        return HasCurrencyId.super.currencyIds();
    }

}
