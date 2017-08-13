package net.meerkat.calculate.price.moneymarket;

import javax.annotation.Nonnull;

import net.meerkat.pricing.ShiftablePrice;
import net.meerkat.pricing.shifts.SecurityShifts;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.price.Price;

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
