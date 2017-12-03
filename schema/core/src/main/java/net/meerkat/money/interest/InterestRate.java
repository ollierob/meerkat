package net.meerkat.money.interest;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.interest.fixed.SimpleFixedInterestRate;
import net.meerkat.money.interest.interpolation.InterestRateInterpolator;
import net.meerkat.numeric.percentage.Percentage;
import net.meerkat.temporal.date.count.DateArithmetic;
import net.meerkat.temporal.date.interim.CompleteInterval;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

/**
 *
 * @author Ollie
 * @see InterestRateSnapshot
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

    default <C extends CurrencyId> Money<C> accrue(
            final Money<C> money,
            final ChronoLocalDate from,
            final ChronoLocalDate to,
            final InterestRateInterpolator interpolator) {
        return this.accrue(money, LocalDate.from(from), LocalDate.from(to), interpolator);
    }

    /**
     * @return the amount of interest accrued.
     */
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

    default <C extends CurrencyId> Money<C> discount(
            final Money<C> money,
            final ChronoLocalDate earlier,
            final ChronoLocalDate later,
            final InterestRateInterpolator interpolator) {
        return this.discount(money, LocalDate.from(earlier), LocalDate.from(later), interpolator);
    }

    @Nonnull
    @CheckReturnValue
    InterestRate plus(@Nonnull Percentage bump);

    @Nonnull
    @CheckReturnValue
    InterestRate times(@Nonnull Percentage bump);

    @Nonnull
    @CheckReturnValue
    default InterestRate minus(@Nonnull final Percentage bump) {
        return this.plus(bump.negate());
    }

    @Override
    @Deprecated
    default InterestRate resolve(final InterestRateSnapshot provider) {
        return this;
    }

    static InterestRate zero() {
        return SimpleFixedInterestRate.ZERO_ACT_ACT;
    }

}
