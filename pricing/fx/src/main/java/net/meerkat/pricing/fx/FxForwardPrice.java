package net.meerkat.pricing.fx;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.numeric.decimal.BigDecimalFraction;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface FxForwardPrice<C extends CurrencyId> extends FxPrice<C> {

    @Nonnull
    Number forwardPoints();

    @Nonnull
    default Number forwardPointsPips() {
        return BigDecimalFraction.of(this.forwardPoints()).times(1000);
    }

    @Override
    default Money<C> value() {
        return Money.of(this.currencyId(), this.forwardPoints());
    }

    interface Shiftable<C extends CurrencyId> extends FxForwardPrice<C>, FxPrice.Shiftable<C> {

    }

}
