package net.meerkat.money.interest;

import java.time.LocalDate;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.interest.interpolation.InterestRateInterpolator;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.temporal.date.count.DateArithmetic;
import net.ollie.goat.temporal.date.interim.CompleteInterval;

/**
 *
 * @author Ollie
 */
public interface InterestRate extends InterestRateOrId {

    @Nonnull
    DateArithmetic dateArithmetic();

    @Nonnull
    <C extends CurrencyId> Money<C> accrue(Money<C> money, LocalDate from, LocalDate until, InterestRateInterpolator interpolator);

    @Nonnull
    default <C extends CurrencyId> Money<C> accrue(final Money<C> money, final CompleteInterval interval, final InterestRateInterpolator interpolator) {
        return this.accrue(money, interval.first(), interval.last(), interpolator);
    }

    @Nonnull
    default <C extends CurrencyId> Money<C> accrued(final Money<C> money, final LocalDate earlier, final LocalDate later, final InterestRateInterpolator interpolator) {
        return this.accrue(money, earlier, later, interpolator).minus(money);
    }

    @Nonnull
    default <C extends CurrencyId> Money<C> discount(
            final Money<C> money,
            final LocalDate earlier,
            final LocalDate later,
            final InterestRateInterpolator interpolator) {
        return this.accrue(money, later, earlier, interpolator);
    }

    @Nonnull
    @CheckReturnValue
    InterestRate plus(@Nonnull Percentage bump);

    @Nonnull
    @CheckReturnValue
    default InterestRate minus(@Nonnull final Percentage bump) {
        return this.plus(bump.negate());
    }

    @Override
    @Deprecated
    default InterestRate resolve(final InterestRateProvider provider) {
        return this;
    }

}
