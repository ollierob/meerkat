package net.meerkat.pricing.fx;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Price;
import net.meerkat.pricing.ShiftablePrice;
import net.meerkat.pricing.shifts.ExchangeRateShifts;
import net.meerkat.pricing.shifts.SecurityShifts;

/**
 *
 * @author ollie
 */
public interface FxPrice<C extends CurrencyId> extends Price.Valued<C> {

    interface Shiftable<C extends CurrencyId> extends FxPrice<C>, ShiftablePrice<C> {

        @Override
        default ShiftablePrice<C> shift(final SecurityShifts shifts) {
            return shifts instanceof ExchangeRateShifts
                    ? this.shift((ExchangeRateShifts) shifts)
                    : this;
        }

        Shiftable<C> shift(ExchangeRateShifts shifts);

    }

}
