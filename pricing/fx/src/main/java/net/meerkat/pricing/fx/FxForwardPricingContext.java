package net.meerkat.pricing.fx;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.money.interest.fixed.FixedInterestRate;

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
