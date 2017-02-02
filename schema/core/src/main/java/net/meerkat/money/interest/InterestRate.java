package net.meerkat.money.interest;

import java.time.LocalDate;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.temporal.date.count.DateArithmetic;
import net.ollie.goat.temporal.date.interim.CompleteInterval;

/**
 *
 * @author Ollie
 */
public interface InterestRate {

    @Nonnull
    DateArithmetic dateArithmetic();

    @Nonnull
    <C extends CurrencyId> Money<C> accrue(Money<C> money, LocalDate from, LocalDate until);

    @Nonnull
    default <C extends CurrencyId> Money<C> accrue(final Money<C> money, final CompleteInterval interval) {
        return this.accrue(money, interval.first(), interval.last());
    }

    @Nonnull
    default <C extends CurrencyId> Money<C> accrued(final Money<C> money, final LocalDate earlier, final LocalDate later) {
        return this.accrue(money, earlier, later).minus(money);
    }

    @Nonnull
    default <C extends CurrencyId> Money<C> discount(final Money<C> money, final LocalDate earlier, final LocalDate later) {
        return this.accrue(money, later, earlier);
    }

    @Nonnull
    @CheckReturnValue
    InterestRate plus(Percentage bump);

    @Nonnull
    @CheckReturnValue
    default InterestRate minus(final Percentage bump) {
        return this.plus(bump.negate());
    }

}
