package net.meerkat.pricing.fx;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.price.Price;
import net.meerkat.pricing.ShiftablePrice;
import net.meerkat.pricing.shifts.InstrumentPriceShifts;
import net.meerkat.pricing.shifts.interest.InterestRateShifts;

/**
 *
 * @author ollie
 */
public interface FxPrice<C extends CurrencyId> extends Price.Valued<C> {

    interface Shiftable<C extends CurrencyId> extends FxPrice<C>, ShiftablePrice<C> {

        @Override
        default FxPrice.Shiftable<C> shift(final InstrumentPriceShifts shifts) {
            return shifts instanceof InterestRateShifts
                    ? this.shift((InterestRateShifts) shifts)
                    : this;
        }

        FxPrice.Shiftable<C> shift(InterestRateShifts shifts);

    }

}
