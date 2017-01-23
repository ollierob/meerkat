package net.ollie.meerkat.calculate.price.interest.repo;

import java.util.Optional;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import net.meerkat.money.Money;
import net.ollie.meerkat.calculate.price.SecurityPrice;
import net.ollie.meerkat.calculate.price.ShiftableSecurityPrice;
import net.ollie.meerkat.calculate.price.shifts.SecurityShifts;
import net.meerkat.money.currency.CurrencyId;

/**
 *
 * @author ollie
 */
public interface RepoPrice<C extends CurrencyId> extends SecurityPrice<C> {

    @Override
    default Money<C> dirty() {
        return this.clean();
    }

    @Override
    default EvaluatedRepoPrice<C> evaluate() {
        return new EvaluatedRepoPrice<>(this.clean(), this.dirty());
    }

    interface Shiftable<C extends CurrencyId> extends RepoPrice<C>, ShiftableSecurityPrice<C> {

        @Override
        default Optional<RepoPrice.Shiftable<C>> shift(final SecurityShifts shifts) {
            return shifts.cast(RepoShifts.class).map(this::shift);
        }

        @CheckReturnValue
        @Nonnull
        RepoPrice.Shiftable<C> shift(RepoShifts shifts);

    }

}
