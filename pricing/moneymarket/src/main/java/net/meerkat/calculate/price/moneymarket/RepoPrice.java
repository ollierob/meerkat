package net.meerkat.calculate.price.moneymarket;

import javax.annotation.Nonnull;

import net.meerkat.calculate.price.Price;
import net.meerkat.calculate.price.ShiftablePrice;
import net.meerkat.calculate.price.shifts.SecurityShifts;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;

/**
 *
 * @author ollie
 */
public interface RepoPrice<C extends CurrencyId>
        extends Price<C> {

    @Nonnull
    Money<C> price();

    interface Shiftable<C extends CurrencyId>
            extends RepoPrice<C>, ShiftablePrice<C> {

        @Override
        default ShiftablePrice<C> shift(SecurityShifts shifts) {
            return this.shift(RepoShifts.cast(shifts));
        }

        RepoPrice.Shiftable<C> shift(RepoShifts shifts);

    }

}
