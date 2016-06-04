package net.ollie.meerkat.calculate.price.repo;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import net.ollie.goat.money.currency.Currency;
import net.ollie.goat.money.Money;
import net.ollie.meerkat.calculate.price.SecurityPrice;
import net.ollie.meerkat.calculate.price.ShiftableSecurityPrice;
import net.ollie.meerkat.calculate.price.shifts.SecurityShifts;

/**
 *
 * @author ollie
 */
public interface RepoPrice<C extends Currency> extends SecurityPrice<C> {

    @Override
    default Money<C> dirty() {
        return this.clean();
    }

    @Override
    default EvaluatedRepoPrice<C> evaluate() {
        return new EvaluatedRepoPrice<>(this.clean(), this.dirty());
    }

    interface Shiftable<C extends Currency> extends RepoPrice<C>, ShiftableSecurityPrice<C> {

        @Override
        default RepoPrice.Shiftable<C> shift(final SecurityShifts shifts) {
            return this.shift(shifts.definiteCast(RepoShifts.class));
        }

        @CheckReturnValue
        @Nonnull
        RepoPrice.Shiftable<C> shift(RepoShifts shifts);

    }

}
