package net.meerkat.pricing.interest;

import net.meerkat.pricing.interest.shifts.InterestRateDerivativeShifts;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.price.Price;
import net.meerkat.pricing.ShiftablePrice;
import net.meerkat.pricing.shifts.InstrumentShifts;

/**
 *
 * @author Ollie
 */
public interface InterestRateDerivativePrice<C extends CurrencyId> extends Price.Valued<C> {

    interface Shiftable<C extends CurrencyId> extends InterestRateDerivativePrice<C>, ShiftablePrice<C> {

        @Override
        default Shiftable<C> shift(final InstrumentShifts shifts) {
            return this.shift(InterestRateDerivativeShifts.cast(shifts));
        }

        Shiftable<C> shift(InterestRateDerivativeShifts shifts);

    }

}
