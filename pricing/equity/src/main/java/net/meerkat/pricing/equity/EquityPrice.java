package net.meerkat.pricing.equity;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.price.MoneyPrice;
import net.meerkat.pricing.ShiftablePrice;
import net.meerkat.pricing.shifts.InstrumentPriceShifts;

/**
 * @author Ollie
 */
public interface EquityPrice<C extends CurrencyId>
        extends MoneyPrice<C> {

    interface Shiftable<C extends CurrencyId> extends EquityPrice<C>, ShiftablePrice<C> {

        @Override
        Shiftable<C> shift(InstrumentPriceShifts shifts);

    }

}
