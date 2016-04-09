package net.ollie.meerkat.calculate.price.repo;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import net.ollie.meerkat.calculate.price.SecurityPrice;
import net.ollie.meerkat.calculate.price.ShiftableSecurityPrice;
import net.ollie.meerkat.calculate.price.shifts.SecurityShifts;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.money.Money;

/**
 *
 * @author ollie
 */
public interface RepoPrice<C extends CurrencyId> extends SecurityPrice<C> {

    @Override
    default Money<C> dirtyValue() {
        return this.cleanValue();
    }

    @Override
    default EvaluatedRepoPrice<C> evaluate() {
        return new EvaluatedRepoPrice<>(this.cleanValue(), this.dirtyValue());
    }

    interface Shiftable<C extends CurrencyId> extends RepoPrice<C>, ShiftableSecurityPrice<C> {

        @Override
        default RepoPrice.Shiftable<C> shift(final SecurityShifts shifts) {
            return this.shift(shifts.definiteCast(RepoShifts.class));
        }

        @CheckReturnValue
        @Nonnull
        RepoPrice.Shiftable<C> shift(RepoShifts shifts);

    }

}
