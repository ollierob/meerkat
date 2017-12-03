package net.meerkat.instrument.interest.future.stir;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.HasCurrencyId;
import net.meerkat.instrument.interest.future.InterestRateFutureContract;
import net.meerkat.objects.HasName;

import javax.annotation.Nonnull;
import java.time.Period;

/**
 *
 * @author ollie
 */
public interface ShortTermInterestRateFutureContract<C extends CurrencyId>
        extends InterestRateFutureContract<C>, HasName, HasCurrencyId {

    @Nonnull
    Period term();

}
