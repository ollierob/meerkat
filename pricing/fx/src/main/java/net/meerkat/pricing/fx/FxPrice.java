package net.meerkat.pricing.fx;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.price.TwoWayPrice;
import net.meerkat.pricing.ShiftablePrice;
import net.meerkat.pricing.shifts.interest.InterestRateShifts;
import net.meerkat.pricing.shifts.InstrumentShifts;

/**
 *
 * @author ollie
 */
public interface FxPrice<C extends CurrencyId> extends TwoWayPrice<C> {

    interface Shiftable<C extends CurrencyId> extends FxPrice<C>, ShiftablePrice<C> {

        @Override
        default FxPrice.Shiftable<C> shift(final InstrumentShifts shifts) {
            return shifts instanceof InterestRateShifts
                    ? this.shift((InterestRateShifts) shifts)
                    : this;
        }

        FxPrice.Shiftable<C> shift(InterestRateShifts shifts);

    }

}
