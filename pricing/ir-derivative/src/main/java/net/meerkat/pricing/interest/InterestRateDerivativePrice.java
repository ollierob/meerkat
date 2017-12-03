package net.meerkat.pricing.interest;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.price.MoneyPrice;
import net.meerkat.pricing.ShiftablePrice;
import net.meerkat.pricing.interest.shifts.InterestRateDerivativeShifts;
import net.meerkat.pricing.shifts.InstrumentPriceShifts;

/**
 *
 * @author Ollie
 */
public interface InterestRateDerivativePrice<C extends CurrencyId> extends MoneyPrice<C> {

    interface Shiftable<C extends CurrencyId> extends InterestRateDerivativePrice<C>, ShiftablePrice<C> {

        @Override
        default Shiftable<C> shift(final InstrumentPriceShifts shifts) {
            return this.shift(InterestRateDerivativeShifts.cast(shifts));
        }

        Shiftable<C> shift(InterestRateDerivativeShifts shifts);

    }

}
