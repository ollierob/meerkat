package net.ollie.meerkat.calculate.price.repo;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import net.ollie.meerkat.calculate.price.SecurityPrice;
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
    default RepoPrice<C> shift(final SecurityShifts shifts) {
        return this.shift(shifts.definiteCast(RepoShifts.class));
    }

    @CheckReturnValue
    @Nonnull
    RepoPrice<C> shift(RepoShifts shifts);

}
