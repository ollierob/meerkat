package net.ollie.meerkat.numeric.interest;

import java.time.LocalDate;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.time.daycount.AccrualFactor;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.security.Security;

/**
 *
 * @author Ollie
 */
public interface InterestRate extends Security {

    @Nonnull
    AccrualFactor accrual();

    @Nonnull
    Percentage fixing(LocalDate date);

    @Nonnull
    <C extends CurrencyId> Money<C> accrue(Money<C> money, LocalDate start, LocalDate accrualDate);

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
