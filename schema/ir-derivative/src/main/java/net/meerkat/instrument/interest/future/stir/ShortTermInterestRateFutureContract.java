package net.meerkat.instrument.interest.future.stir;

import java.time.Period;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.HasCurrencyId;
import net.meerkat.instrument.interest.future.InterestRateFutureContract;
import net.meerkat.utils.HasName;

/**
 *
 * @author ollie
 */
public interface ShortTermInterestRateFutureContract<C extends CurrencyId>
        extends InterestRateFutureContract<C>, HasName, HasCurrencyId {

    @Nonnull
    Period term();

}
