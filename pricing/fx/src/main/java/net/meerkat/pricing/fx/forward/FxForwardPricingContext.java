package net.meerkat.pricing.fx.forward;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.money.interest.fixed.FixedInterestRate;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface FxForwardPricingContext<B extends CurrencyId, C extends CurrencyId> {

    @Nonnull
    ExchangeRate<B, C> spotRate();

    @Nonnull
    FixedInterestRate baseRate();

    @Nonnull
    FixedInterestRate counterRate();

}
