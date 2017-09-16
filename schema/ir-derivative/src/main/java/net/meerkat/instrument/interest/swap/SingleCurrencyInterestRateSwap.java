package net.meerkat.instrument.interest.swap;

import net.coljate.list.List;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.CurrencyIds;
import net.meerkat.identifier.currency.HasCurrencyId;
import net.meerkat.instrument.interest.swap.leg.InterestRateSwapLeg;

/**
 *
 * @author Ollie
 */
public interface SingleCurrencyInterestRateSwap<C extends CurrencyId> extends InterestRateSwap, HasCurrencyId {

    @Override
    C currencyId();

    @Override
    List<? extends InterestRateSwapLeg<C, C>> legs();

    @Override
    default CurrencyIds currencyIds() {
        return HasCurrencyId.super.currencyIds();
    }

}
