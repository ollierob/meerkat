package net.meerkat.pricing.moneymarket;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.price.Price;
import net.meerkat.pricing.ShiftablePrice;
import net.meerkat.pricing.shifts.SecurityShifts;

/**
 *
 * @author ollie
 */
public interface RepoPrice<C extends CurrencyId>
        extends Price.Valued<C> {

    @Override
    default EvaluatedRepoPrice<C> evaluate() {
        return new EvaluatedRepoPrice<>(this.value());
    }

    interface Shiftable<C extends CurrencyId>
            extends RepoPrice<C>, ShiftablePrice<C> {

        @Override
        default ShiftablePrice<C> shift(SecurityShifts shifts) {
            return this.shift(RepoShifts.cast(shifts));
        }

        RepoPrice.Shiftable<C> shift(RepoShifts shifts);

    }

}